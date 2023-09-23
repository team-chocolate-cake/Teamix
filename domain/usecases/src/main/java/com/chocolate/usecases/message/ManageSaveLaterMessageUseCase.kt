package com.chocolate.usecases.message

import com.chocolate.entities.messages.SavedLaterMessage
import kotlinx.coroutines.flow.Flow
import repositories.TopicsMessageRepository
import javax.inject.Inject

class ManageSaveLaterMessageUseCase @Inject constructor(
    private val topicsMessageRepository: TopicsMessageRepository
) {
    suspend fun addSavedLaterMessage(message: SavedLaterMessage) =
        topicsMessageRepository.saveMessage(message)

    suspend fun getSavedMessages(): Flow<List<SavedLaterMessage>> =
        topicsMessageRepository.getSavedLaterMessages()

    suspend fun deleteSavedMessageById(savedLaterMessageId: String) =
        topicsMessageRepository.deleteSavedLaterMessageById(savedLaterMessageId)

}