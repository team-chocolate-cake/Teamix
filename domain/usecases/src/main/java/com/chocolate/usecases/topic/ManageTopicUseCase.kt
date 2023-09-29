package com.chocolate.usecases.topic

import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.InvalidTopicNameException
import kotlinx.coroutines.flow.Flow
import repositories.SavedLaterRepository
import repositories.TopicRepository
import javax.inject.Inject

class ManageTopicUseCase @Inject constructor(
    private val topicRepository: TopicRepository,
    private val savedLaterRepository: SavedLaterRepository
) {

    suspend fun createTopic(channelId: String, topic: Topic): String {
        return topicRepository.createTopic(channelId = channelId, topic = topic)
    }

    suspend fun getTopicsInChannel(channelId: String): Flow<List<Topic>> {
        return topicRepository.getTopicsInChannel(channelId)
    }

    fun validateTopicName(topicName: String): String {
        return if (topicName.isBlank() || topicName.length > 80
        ) {
            throw InvalidTopicNameException
        } else {
            topicName
        }
    }

    suspend fun addSavedTopic(topic: Topic) =
        savedLaterRepository.saveTopic(topic)

    suspend fun getSavedTopics(): Flow<List<Topic>> =
        savedLaterRepository.getSavedTopics()

    suspend fun deleteTopicById(topicId: String) =
        savedLaterRepository.deleteSavedTopicById(topicId)

}