package com.chocolate.remote.data_source

import android.util.Log
import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.remote.util.Constants
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.chocolate.repository.model.dto.direct_message.Chat
import com.chocolate.repository.model.dto.direct_message.NewChat
import com.google.firebase.Timestamp
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


class DirectMessageRemoteDataSourceImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : DirectMessageRemoteDataSource {
    override suspend fun getChatsByUserId(
        userid: String,
        currentOrgName: String
    ): Flow<List<Chat>> {
        return callbackFlow {
            val listener = firebase.collection(Constants.TEAMIX).document(currentOrgName).collection(Constants.CHATS)
                .whereArrayContains("members", userid)
                .orderBy(Constants.LASTMESSAGEDATE)
                .addSnapshotListener { doc, error ->
                    if (error != null)
                        throw TeamixException(error.message)
                    else {
                        val chats = doc?.map {
                            val members = it.data["members"] as List<String>? ?: emptyList()
                            val secondMember = members.find { it != userid } ?: ""
                            Chat(
                                id = it.data["id"] as String? ?: "",
                                secondMember = secondMember,
                                lastMessage = it.data["lastMessage"] as String? ?: "",
                                lastMessageDate = it.getTimestamp("lastMessageDate")?.toDate()
                                    ?: Date()
                            )
                        } ?: emptyList()
                        trySend(chats)
                    }
                }
            awaitClose { listener.remove() }
        }
    }

    override suspend fun createGroup(userids: List<String>, currentOrgName: String): String {
        val groupCreatedBefore = checkIfGroupCreatedBefore(userids, currentOrgName)
        return if (groupCreatedBefore.isNotEmpty()) groupCreatedBefore else
            suspendCoroutine { cont ->
                val newdoc = firebase.collection(Constants.TEAMIX).document(currentOrgName)
                    .collection(Constants.CHATS)
                    .document()
                newdoc.set(
                    NewChat(
                        id = newdoc.id,
                        members = userids
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
        groupId: String,
        currentOrgName: String
    ): Flow<List<DMMessage>> {
        return callbackFlow {
            val listner = firebase
                .collection(Constants.TEAMIX)
                .document(currentOrgName)
                .collection(Constants.CHATS)
                .document(groupId)
                .collection(Constants.MESSAGES)
                .orderBy(Constants.SENTAT)
                .addSnapshotListener { doc, error ->
                    if (error != null)
                        throw TeamixException(error.message)
                    else {
                        val messages = doc?.map {
                            val g = it.getTimestamp("sentAt") as Timestamp
                            DMMessage(
                                sentAt = (it.getTimestamp("sentAt") as Timestamp?)?.toDate() ?: Date(),
                                sentBy = it.data["sendBy"] as String? ?:"",
                                messageText = it.data["messageText"]as String? ?:"",
                                senderAvatarUrl = it.data["senderAvatarUrl"]as String? ?:"",
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
        message: DMMessage,
        currentOrgName: String,
        currentGroupId: String
    ) {
        firebase
            .collection(Constants.TEAMIX)
            .document(currentOrgName)
            .collection(Constants.CHATS)
            .document(currentGroupId)
            .collection(Constants.MESSAGES)
            .add(message)
            .await()

        firebase.collection(Constants.TEAMIX)
            .document(currentOrgName)
            .collection(Constants.CHATS)
            .document(currentGroupId)
            .update("lastMessage", message.messageText , "lastMessageDate" , message.sentAt)
            .await()
    }
}