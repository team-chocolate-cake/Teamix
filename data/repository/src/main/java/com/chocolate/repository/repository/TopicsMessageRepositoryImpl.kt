package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.entity.Message
import com.chocolate.repository.datasource.remote.TopicMessagesDataSource
import com.chocolate.repository.mappers.toMessage
import com.chocolate.repository.mappers.toMessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.TopicsMessageRepository
import javax.inject.Inject

class TopicsMessageRepositoryImpl @Inject constructor(
    private val topicMessagesDataSource: TopicMessagesDataSource,
) : TopicsMessageRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sendMessageInTopic(
        message: Message,
        topicId: String,
        channelId: String,
        organizationName: String
    ) {
        topicMessagesDataSource.sendMessageInTopic(
            message = message.toMessageDto(),
            topicId = topicId,
            channelId = channelId,
            "teamixOrganization"
        )
    }

    override suspend fun getMessagesFromTopic(
        topicId: String,
        channelId: String,
        organizationName: String
    ): Flow<List<Message>> {
        return topicMessagesDataSource.getMessagesFromTopic(
            topicId,
            channelId,
            "teamixOrganization"
        ).map { it.toMessage() }
    }
}