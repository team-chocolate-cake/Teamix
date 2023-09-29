package com.chocolate.repository.datasource.remote

import com.chocolate.repository.model.dto.message.SavedLaterMessageDto
import com.chocolate.repository.model.dto.topic.TopicDto
import kotlinx.coroutines.flow.Flow

interface SavedLaterDataSource {
    suspend fun addSavedLaterMessage(
        organizationName: String,
        savedLaterMessage: SavedLaterMessageDto
    )

    suspend fun getSavedLaterMessages(
        organizationName: String,
        memberId: String
    ): Flow<List<SavedLaterMessageDto>>

    suspend fun deleteSavedLaterMessageById(
        organizationName: String,
        memberId: String,
        savedLaterMessageId: String
    )

    suspend fun getSavedTopics(
        organizationName: String,
        memberId: String
    ): Flow<List<TopicDto>>

    suspend fun deleteSavedTopicById(
        organizationName: String,
        memberId: String,
        topicId: String
    )

    suspend fun addSavedTopic(
        organizationName: String,
        topic: TopicDto,
        memberId: String,
    )
}