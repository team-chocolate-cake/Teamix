package com.chocolate.repository.service.local

import com.chocolate.repository.dto.local.stream.StreamLocalDto

interface StreamLocalDataSource {
    suspend fun insertStream(stream: StreamLocalDto)

    suspend fun getStreamById(id: String): StreamLocalDto?

    suspend fun deleteSavedStream(stream: StreamLocalDto)
}