package com.chocolate.usecases.usecase.message

import com.chocolate.entities.entity.Message
import kotlinx.coroutines.flow.Flow
import com.chocolate.usecases.repositories.SavedLaterRepository
import com.chocolate.usecases.repositories.TopicsMessageRepository
import javax.inject.Inject

class ManageTopicMessagesUseCase @Inject constructor(
    private val topicsMessageRepository: TopicsMessageRepository,
    private val savedLaterRepository: SavedLaterRepository
) {
    suspend fun sendMessage(message: Message, channelId: String, topicId: String) {
        topicsMessageRepository.sendMessageInTopic(message, topicId, channelId, "")
    }

    suspend fun getMessages(topicId: String, channelId: String): Flow<List<Message>> {
        return topicsMessageRepository.getMessagesFromTopic(topicId, channelId, "")
    }

    suspend fun addSavedLaterMessage(message: Message) =
        savedLaterRepository.saveMessage(message)

}