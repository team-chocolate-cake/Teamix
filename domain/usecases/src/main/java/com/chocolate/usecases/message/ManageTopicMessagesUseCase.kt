package com.chocolate.usecases.message

import com.chocolate.entities.entity.Message
import com.chocolate.entities.entity.SavedMessage
import kotlinx.coroutines.flow.Flow
import repositories.SavedLaterRepository
import repositories.TopicsMessageRepository
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

    suspend fun addSavedLaterMessage(message: SavedMessage) =
        savedLaterRepository.saveMessage(message)

    suspend fun getSavedMessages(): Flow<List<SavedMessage>> =
        savedLaterRepository.getSavedLaterMessages()

    suspend fun deleteSavedMessageById(savedLaterMessageId: String) =
        savedLaterRepository.deleteSavedLaterMessageById(savedLaterMessageId)

}