package com.chocolate.usecases.topic

import com.chocolate.entities.entity.Topic
import repositories.TopicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageTopicUseCase @Inject constructor(
    private val topicRepository: TopicRepository,
) {

    suspend fun createTopic(channelId: String, topic: Topic): String {
        return topicRepository.createTopic(channelId = channelId, topic = topic)
    }

    suspend fun getTopicsInChannel(channelId: String): Flow<List<Topic>> {
        return topicRepository.getTopicsInChannel(channelId)
    }
}