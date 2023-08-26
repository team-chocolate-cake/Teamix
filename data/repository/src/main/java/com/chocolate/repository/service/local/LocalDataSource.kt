package com.chocolate.repository.service.local

import com.chocolate.repository.model.localDto.message.SavedMessageLocalDto
import com.chocolate.repository.model.localDto.stream.StreamLocalDto
import com.chocolate.repository.model.localDto.users.UserLocalDto

interface LocalDataSource {

    suspend fun upsertUserData(userLocalDto: UserLocalDto)

    suspend fun getCurrentUserData(): UserLocalDto?

    suspend fun insertStream(stream: StreamLocalDto)

    suspend fun getStreamById(id: String): StreamLocalDto?

    suspend fun deleteStream(streamId: Int)

    suspend fun deleteDataBase()

    suspend fun saveMessage(message: SavedMessageLocalDto)

    suspend fun getSavedMessages(): List<SavedMessageLocalDto>

    suspend fun deleteSavedMessageById(id: Int)
}