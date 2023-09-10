package com.chocolate.repository.datastore.realtime

import com.chocolate.repository.datastore.realtime.model.MessageDto
import com.chocolate.repository.datastore.realtime.model.TopicDto
import kotlinx.coroutines.flow.Flow

interface TopicDataSource {

    suspend fun createTopic(channelName:String, topic: TopicDto, organizationName:String)
    suspend fun getTopicsInAChannel(channelId: String, organizationName:String):Flow<List<TopicDto>>
    suspend fun sendMessage(message:MessageDto,channelId:Int,organizationName: String,topicId:String)

    suspend fun getMessages(topicId: Int,channelId:Int,organizationName: String): Flow<List<MessageDto>>
}