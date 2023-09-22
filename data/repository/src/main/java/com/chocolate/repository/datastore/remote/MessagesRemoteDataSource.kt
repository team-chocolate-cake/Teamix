package com.chocolate.repository.datastore.remote

import com.chocolate.repository.datastore.realtime.model.MessageDto
import kotlinx.coroutines.flow.Flow

interface MessagesRemoteDataSource {

    suspend fun sendMessageInTopic(
        message: MessageDto,
        topicId: String,
        channelId: String,
        organizationName: String,
    )

    suspend fun getMessagesFromTopic(
        topicId: String,
        channelId: String,
        organizationName: String
    ): Flow<List<MessageDto>>

}