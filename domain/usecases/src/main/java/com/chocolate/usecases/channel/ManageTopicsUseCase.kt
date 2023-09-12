package com.chocolate.usecases.channel

import com.chocolate.entities.topic.Topic
import repositories.TopicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageTopicsUseCase @Inject constructor(private val topicRepository: TopicRepository) {

    suspend fun createTopic( channelId:String, topic: Topic) {
        topicRepository.createTopic( channelId, topic,"")

    }

    suspend fun getTopicsInChannel(channelId:String): Flow<List<Topic>>  {
     return   topicRepository.getTopicsInChannel(channelId,"")
    }
}