package com.chocolate.usecases.message

import com.chocolate.entities.messages.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.MessagesRepository
import javax.inject.Inject

class ManageChannelMessages @Inject constructor(
    private val messagesRepository: MessagesRepository
) {
    suspend fun sendMessage(text:String,channelId:String,userId:String,senderName:String,senderImage:String){
        messagesRepository.sendStreamMessage(text, channelId, userId,senderName,senderImage)
    }
    suspend fun getMessages(channelId: String): Flow<List<Message>> {
        return messagesRepository.getMessages(channelId).map { message -> message!!.sortedByDescending { it.id } }
    }
}