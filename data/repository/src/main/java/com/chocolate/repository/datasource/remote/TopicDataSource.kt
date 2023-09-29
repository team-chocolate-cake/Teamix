package com.chocolate.repository.datasource.remote

import com.chocolate.repository.model.dto.topic.TopicDto
import kotlinx.coroutines.flow.Flow

interface TopicDataSource {
    suspend fun createTopic(channelId: String, topic: TopicDto, organizationName: String): String

    suspend fun getTopicsInAChannel(
        channelId: String,
        organizationName: String
    ): Flow<List<TopicDto>>

    suspend fun addSavedTopic(
        organizationName: String,
        topic: TopicDto,
        memberId: String,
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
}