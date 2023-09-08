package com.chocolate.remote.data_source

import com.chocolate.entities.Organization
import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DirectMessageRemoteDataSourceImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : DirectMessageRemoteDataSource {
    override suspend fun getChatsByUserId(userid: String): List<Chat> {
        return suspendCoroutine { cont ->
            firebase.collection("group")
                .where(Filter.arrayContains("members", userid))
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
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(messageText: String, sentAt: String, currentGroupId: String) {
        TODO("Not yet implemented")
    }
}