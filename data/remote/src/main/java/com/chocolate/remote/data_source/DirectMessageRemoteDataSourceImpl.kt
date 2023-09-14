package com.chocolate.remote.data_source

import com.chocolate.entities.directMessage.MessageEntity
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.remote.util.Constants
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.chocolate.repository.model.dto.directmessage.ChatDto
import com.chocolate.repository.model.dto.directmessage.NewChat
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class DirectMessageRemoteDataSourceImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : DirectMessageRemoteDataSource {
    override suspend fun getChatsByUserId(
        memberId: String,
        currentOrganizationName: String
    ): Flow<List<ChatDto>> {
        return callbackFlow {
            val listener = firebase.collection(Constants.TEAMIX).document(currentOrganizationName).collection(Constants.CHATS)
                .whereArrayContains("members", memberId)
                .orderBy(Constants.LASTMESSAGEDATE)
                .addSnapshotListener { doc, error ->
                    if (error != null)
                        throw TeamixException(error.message)
                    else {
                        val chatDtos = doc?.map {
                            val members = it.data["members"] as List<String>? ?: emptyList()
                            val secondMember = members.find { it != memberId } ?: ""
                            ChatDto(
                                id = it.data["id"] as String? ?: "",
                                secondMemberId = secondMember,
                                lastMessage = it.data["lastMessage"] as String? ?: "",
                                lastMessageDate = it.getTimestamp("lastMessageDate")?.toDate()
                                    ?: Date()
                            )
                        } ?: emptyList()
                        trySend(chatDtos)
                    }
                }
            awaitClose { listener.remove() }
        }
    }

    override suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String {
        val groupCreatedBefore = checkIfGroupCreatedBefore(memberIds, currentOrganizationName)
        return if (groupCreatedBefore.isNotEmpty()) groupCreatedBefore else
            suspendCoroutine { cont ->
                val newdoc = firebase.collection(Constants.TEAMIX).document(currentOrganizationName)
                    .collection(Constants.CHATS)
                    .document()
                newdoc.set(
                    NewChat(
                        id = newdoc.id,
                        members = memberIds
                    )
                )
            }
    }

    private suspend fun checkIfGroupCreatedBefore(
        userids: List<String>,
        currentOrgName: String
    ): String {
        return suspendCoroutine { cont ->
            firebase.collection(Constants.TEAMIX).document(currentOrgName).collection(Constants.CHATS)
                .whereEqualTo("members", userids)
                .get()
                .addOnSuccessListener { doc ->
                    val chat = doc.map {
                        it.data["id"] as String? ?: ""
                    }
                    val groupId = if (chat.isEmpty()) "" else chat.first()
                    cont.resume(groupId)
                }.addOnFailureListener {
                    cont.resumeWithException(it)
                }
        }
    }

    override suspend fun fetchMessagesByGroupId(
        chatId: String,
        currentOrganizationName: String
    ): Flow<List<MessageEntity>> {
        return callbackFlow {
            val listner = firebase
                .collection(Constants.TEAMIX)
                .document(currentOrganizationName)
                .collection(Constants.CHATS)
                .document(chatId)
                .collection(Constants.MESSAGES)
                .orderBy(Constants.SENTAT)
                .addSnapshotListener { doc, error ->
                    if (error != null)
                        throw TeamixException(error.message)
                    else {
                        val messages = doc?.map {
                            val g = it.getTimestamp("sentAt") as Timestamp
                            MessageEntity(
                                sentAt = (it.getTimestamp("sentAt") as Timestamp?)?.toDate() ?: Date(),
                                sentBy = it.data["sendBy"] as String? ?:"",
                                messageContent = it.data["messageContent"]as String? ?:"",
                                senderImageUrl = it.data["senderImageUrl"]as String? ?:"",
                                senderFullName = it.data["senderFullName"]as String? ?:""
                            )
                        }
                        if (messages != null) {
                            trySend(messages)
                        }
                    }
                }
            awaitClose{listner.remove()}
        }
    }

    override suspend fun sendMessage(
        message: MessageEntity,
        currentOrganizationName: String,
        currentChatId: String
    ) {
        firebase
            .collection(Constants.TEAMIX)
            .document(currentOrganizationName)
            .collection(Constants.CHATS)
            .document(currentChatId)
            .collection(Constants.MESSAGES)
            .add(message)
            .await()

        firebase.collection(Constants.TEAMIX)
            .document(currentOrganizationName)
            .collection(Constants.CHATS)
            .document(currentChatId)
            .update("lastMessage", message.messageContent , "lastMessageDate" , message.sentAt)
            .await()
    }
}