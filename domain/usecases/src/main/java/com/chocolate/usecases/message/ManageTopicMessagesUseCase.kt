package com.chocolate.usecases.message

import com.chocolate.entities.messages.Message
import kotlinx.coroutines.flow.Flow
import repositories.TopicsMessageRepository
import javax.inject.Inject

class ManageTopicMessagesUseCase @Inject constructor(
    private val topicsMessageRepository: TopicsMessageRepository
) {
    suspend fun sendMessage(message:Message,channelId:String,topicId:String,){
        topicsMessageRepository.sendMessageInTopic(message,topicId,channelId,"")
    }

    suspend fun getMessages( topicId: String,channelId: String): Flow<List<Message>> {
        return topicsMessageRepository.getMessagesFromTopic(topicId,channelId,"")
            //.map { message -> message.sortedByDescending { it.id } }
    }


}