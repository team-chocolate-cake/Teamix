package com.chocolate.remote.data_source

import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.chocolate.repository.model.dto.direct_message.Chat
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

const val CHATS = "chats"
const val TEAMIX = "teamix"
const val MEMBERS = "members"
const val MESSAGES = "messages"
const val SENT_AT = "sentAt"

class DirectMessageRemoteDataSourceImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : DirectMessageRemoteDataSource {
    override suspend fun getChatsByUserId(userid: String, currentOrgName: String): List<Chat> {
        return suspendCoroutine { cont ->
            firebase.collection(TEAMIX).document(currentOrgName).collection(CHATS)
                .where(Filter.arrayContains(MEMBERS, userid))
                .get()
                .addOnSuccessListener { doc ->
                    val chats = doc?.map {
                        val members = it.data["members"] as List<String>
                        val secondMember = members.find { it != userid } ?: ""
                        Chat(
                            id = it.data["id"] as String,
                            secondMember = secondMember,
                            lastMessage = it.data["lastMessage"] as String,
                            lastMessageDate = it.data["lastMessageDate"] as String
                        )
                    } ?: emptyList()
                    cont.resume(chats)
                }.addOnFailureListener {
                    cont.resumeWithException(it)
                }
        }
    }

    override suspend fun fetchMessagesByGroupId(
        groupId: String,
        currentOrgName: String
    ): List<DMMessage> {
        return suspendCoroutine { cont ->
            firebase
                .collection(TEAMIX)
                .document(currentOrgName)
                .collection(CHATS)
                .document(groupId)
                .collection(MESSAGES)
                .orderBy(SENT_AT)
                .get()
                .addOnSuccessListener { doc ->
                    val messages = doc?.toObjects<DMMessage>() ?: emptyList()
                    cont.resume(messages)
                }.addOnFailureListener {
                    cont.resumeWithException(it)
                }
        }
    }

    override suspend fun sendMessage(message: DMMessage, currentGroupId: String) {
        firebase
            .collection(CHATS)
            .document(currentGroupId)
            .collection(MESSAGES)
            .add(message)
    }
}