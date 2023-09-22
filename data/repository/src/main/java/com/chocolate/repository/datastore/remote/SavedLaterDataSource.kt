package com.chocolate.repository.datastore.remote

import com.chocolate.repository.model.dto.message.SavedLaterMessageDto
import kotlinx.coroutines.flow.Flow

interface SavedLaterDataSource {
    suspend fun addSavedLaterMessage(
        organizationName: String,
        savedLaterMessage: SavedLaterMessageDto
    )

    suspend fun getSavedLaterMessages(
        organizationName: String,
        memberId: String
    ): Flow<List<SavedLaterMessageDto>>

    suspend fun deleteSavedLaterMessageById(
        organizationName: String,
        memberId: String,
        savedLaterMessageId: String
    )

}