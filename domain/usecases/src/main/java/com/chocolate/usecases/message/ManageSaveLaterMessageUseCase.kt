package com.chocolate.usecases.message

import com.chocolate.entities.messages.SavedLaterMessage
import kotlinx.coroutines.flow.Flow
import repositories.MessagesRepository
import javax.inject.Inject

class ManageSaveLaterMessageUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {
    suspend fun addSavedLaterMessage(message: SavedLaterMessage) =
        messagesRepository.saveMessage(message)

    suspend fun getSavedMessages(): Flow<List<SavedLaterMessage>> =
        messagesRepository.getSavedLaterMessages()

    suspend fun deleteSavedMessageById(savedLaterMessageId: String) =
        messagesRepository.deleteSavedLaterMessageById(savedLaterMessageId)

}