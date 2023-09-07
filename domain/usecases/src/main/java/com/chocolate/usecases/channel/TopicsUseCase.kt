package com.chocolate.usecases.channel

import repositories.TopicRepository
import javax.inject.Inject

class TopicsUseCase @Inject constructor(private val topicRepository: TopicRepository) {

    suspend fun createTopic(channelId:String,content:String,senderId:String){
        topicRepository.createTopic(channelId,content,senderId)
    }

}