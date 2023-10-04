package com.chocolate.usecases.repositories

import com.chocolate.entities.entity.Message
import kotlinx.coroutines.flow.Flow

interface TopicsMessageRepository {

    suspend fun sendMessageInTopic(
        message: Message,
        topicId: String,
        channelId: String,
        organizationName: String,
    )

    suspend fun getMessagesFromTopic(
        topicId: String,
        channelId: String,
        organizationName: String
    ): Flow<List<Message>>

}