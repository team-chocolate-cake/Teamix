package com.chocolate.repository.datasource.remote

import com.chocolate.repository.model.dto.TopicDto
import kotlinx.coroutines.flow.Flow

interface TopicDataSource {
    suspend fun createTopic(channelId: String, topic: TopicDto, organizationName: String): String

    suspend fun getTopicsInAChannel(
        channelId: String,
        organizationName: String
    ): Flow<List<TopicDto>>
}