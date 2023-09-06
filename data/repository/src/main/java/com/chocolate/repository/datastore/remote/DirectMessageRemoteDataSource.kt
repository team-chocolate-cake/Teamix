package com.chocolate.repository.datastore.remote

import com.chocolate.entities.directMessage.DMMessage

interface DirectMessageRemoteDataSource {

    fun getChatsByUserId(userid: String): List<String>

    fun fetchMessagesByGroupId(groupId: String) :List<DMMessage>

    fun sendMessage(messageText: String, sentAt: String, currentGroupId: String)

}