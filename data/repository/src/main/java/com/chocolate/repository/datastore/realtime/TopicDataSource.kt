package com.chocolate.repository.datastore.realtime

import com.chocolate.repository.datastore.realtime.model.TopicDto
import kotlinx.coroutines.flow.Flow

interface TopicDataSource {

    suspend fun createTopic(channelId: String, topic: TopicDto, organizationName: String)
    suspend fun getTopicsInAChannel(channelId: String, organizationName: String): Flow<List<TopicDto>>

}