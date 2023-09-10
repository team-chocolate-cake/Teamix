package com.chocolate.usecases.channel

import com.chocolate.entities.messages.Topic
import repositories.TopicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopicsUseCase @Inject constructor(private val topicRepository: TopicRepository) {

    suspend fun createTopic(channelId: String, content: String, senderId: String) {
        topicRepository.createTopic(channelId, content, senderId)
    }

    suspend fun getTopicWithMessagesInAChannel(): Flow<List<Topic>>  {
     return   topicRepository.getTopicsInAChannel(channelId = "I hope it works")
    }
}