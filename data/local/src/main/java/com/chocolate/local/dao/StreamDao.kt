package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.stream.StreamLocalDto

@Dao
interface StreamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStream(stream: StreamLocalDto)


    @Query("SELECT * FROM stream_table WHERE id = :id")
    suspend fun getStreamById(id: String): StreamLocalDto?


    @Delete
    suspend fun deleteSavedStream(stream: StreamLocalDto)

}