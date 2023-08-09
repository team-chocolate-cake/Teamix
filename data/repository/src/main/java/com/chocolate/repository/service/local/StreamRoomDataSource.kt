package com.chocolate.repository.service.local

import com.chocolate.repository.model.localDto.stream.StreamLocalDto

interface StreamRoomDataSource {
    suspend fun insertStream(stream: StreamLocalDto)

    suspend fun getStreamById(id: String): StreamLocalDto?

    suspend fun deleteStream(streamId: Int)
}