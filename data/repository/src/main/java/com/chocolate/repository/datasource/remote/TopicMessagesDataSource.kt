package com.chocolate.repository.datasource.remote

import com.chocolate.repository.model.dto.MessageDto
import kotlinx.coroutines.flow.Flow

interface TopicMessagesDataSource {
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