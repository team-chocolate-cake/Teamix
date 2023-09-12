package com.chocolate.usecases.message

import com.chocolate.entities.messages.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.MessagesRepository
import javax.inject.Inject

class ManageTopicMessagesUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {
    suspend fun sendMessage(message:Message,channelId:String,topicId:String,){
        messagesRepository.sendMessageInTopic(message,topicId,channelId,"")
    }

    suspend fun getMessages( topicId: String,channelId: String): Flow<List<Message>> {
        return messagesRepository.getMessagesFromTopic(topicId,channelId,"")
            //.map { message -> message.sortedByDescending { it.id } }
    }


}