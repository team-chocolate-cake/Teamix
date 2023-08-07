package com.chocolate.local.dao.stream

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.chocolate.repository.dto.local.stream.StreamLocalDto

@Dao
interface StreamDao {

    @Upsert
    suspend fun upsertStream(stream: StreamLocalDto)

    @Query("SELECT * FROM stream_table WHERE id = :id")
    suspend fun getStreamById(id: String): StreamLocalDto?

    @Query("DELETE FROM stream_table WHERE id = :streamId")
    suspend fun deleteStream(streamId: Int)

}