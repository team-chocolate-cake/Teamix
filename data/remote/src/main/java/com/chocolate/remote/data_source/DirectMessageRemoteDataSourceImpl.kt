package com.chocolate.remote.data_source

import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

const val GROUPS = "groups"
const val MEMBERS = "members"
const val MESSAGES = "messages"
const val SENT_AT = "sentAt"

class DirectMessageRemoteDataSourceImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : DirectMessageRemoteDataSource {
    override suspend fun getChatsByUserId(userid: String): List<Chat> {
        return suspendCoroutine { cont ->
            firebase.collection(GROUPS)
                .where(Filter.arrayContains(MEMBERS, userid))
                .get()
                .addOnSuccessListener { doc ->
                    val chats = doc?.toObjects<Chat>() ?: emptyList()
                    cont.resume(chats)
                }.addOnFailureListener {
                    cont.resumeWithException(it)
                }
        }
    }

    override suspend fun fetchMessagesByGroupId(groupId: String): List<DMMessage> {
        return suspendCoroutine { cont ->
            firebase
                .collection(GROUPS)
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
            .collection(GROUPS)
            .document(currentGroupId)
            .collection(MESSAGES)
            .add(message)
    }
}