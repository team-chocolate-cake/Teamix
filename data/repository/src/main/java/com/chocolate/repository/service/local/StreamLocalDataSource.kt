package com.chocolate.repository.service.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.stream.StreamLocalDto

interface StreamLocalDataSource {
    suspend fun insertStream(stream: StreamLocalDto)

    suspend fun getStreamById(id: String): StreamLocalDto?

    suspend fun deleteSavedStream(stream: StreamLocalDto)
}