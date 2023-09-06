package com.chocolate.remote.data_source

import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DirectMessageRemoteDataSourceImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : DirectMessageRemoteDataSource {
    override fun getChatsByUserId(userid: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun fetchMessagesByGroupId(groupId: String): List<DMMessage> {
        TODO("Not yet implemented")
    }

    override fun sendMessage(messageText: String, sentAt: String, currentGroupId: String) {
        TODO("Not yet implemented")
    }
}