package com.chocolate.remote.datasource

import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.getRandomId
import com.chocolate.repository.model.dto.message.MessageDto
import com.chocolate.repository.datasource.remote.DirectMessageDataSource
import com.chocolate.repository.model.dto.directmessage.ChatDto
import com.chocolate.repository.model.dto.directmessage.NewChat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DirectMessageFirebaseDataSource @Inject constructor(
    private val firebase: FirebaseFirestore
) : DirectMessageDataSource {
    override suspend fun getChatsByMemberId(
        memberId: String,
        currentOrganizationName: String
    ): Flow<List<ChatDto>> {
        return callbackFlow {
            val listener = firebase
                .collection(Constants.TEAMIX)
                .document(currentOrganizationName)
                .collection(Constants.CHATS)
                .whereArrayContains("members", memberId)
                .orderBy(Constants.LAST_MESSAGE_DATE)
                .addSnapshotListener { chatsSnapShots, exceptions ->
                    exceptions?.let { close(it) }
                    val chatDtos = chatsSnapShots?.map {
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
            awaitClose { listener.remove() }
        }
    }

    override suspend fun createGroup(
        memberIds: List<String>,
        currentOrganizationName: String
    ): String {
        val groupCreatedBefore = checkIfGroupCreatedBefore(memberIds, currentOrganizationName)
        return if (groupCreatedBefore.isNotEmpty()) groupCreatedBefore else {
            val newDoc = firebase.collection(Constants.TEAMIX).document(currentOrganizationName)
                .collection(Constants.CHATS)
                .document()
            newDoc.set(
                NewChat(
                    id = newDoc.id,
                    members = memberIds
                )
            ).await()
            return newDoc.id
        }
    }

    private suspend fun checkIfGroupCreatedBefore(
        membersId: List<String>,
        currentOrgName: String
    ): String {
        return suspendCoroutine { cont ->
            firebase.collection(Constants.TEAMIX).document(currentOrgName)
                .collection(Constants.CHATS)
                .whereEqualTo("members", membersId)
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
    ): Flow<List<MessageDto>> {
        return callbackFlow {
            val listener = firebase
                .collection(Constants.TEAMIX)
                .document(currentOrganizationName)
                .collection(Constants.CHATS)
                .document(chatId)
                .collection(Constants.MESSAGES)
                .addSnapshotListener { doc, error ->
                    if (error != null)
                        throw TeamixException(error.message)
                    val messages = doc?.toObjects<MessageDto>()
                    messages?.let { trySend(it) }
                }
            awaitClose { listener.remove() }
        }
    }

    override suspend fun sendMessage(
        messageDto: MessageDto,
        currentOrganizationName: String,
        currentChatId: String
    ) {
        val messageId = getRandomId()
        val message = MessageDto(
            id = messageId.toString(),
            content = messageDto.content,
            userId = messageDto.userId,
            senderName = messageDto.senderName,
            senderImage = messageDto.senderImage,
            timestamp = messageDto.timestamp,
        )
        firebase
            .collection(Constants.TEAMIX)
            .document(currentOrganizationName)
            .collection(Constants.CHATS)
            .document(currentChatId)
            .collection(Constants.MESSAGES)
            .document(messageId.toString())
            .set(message)
            .await()

        firebase.collection(Constants.TEAMIX)
            .document(currentOrganizationName)
            .collection(Constants.CHATS)
            .document(currentChatId)
            .update("lastMessage", message.content, "lastMessageDate", message.timestamp)
            .await()
    }
}
