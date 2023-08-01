package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.local.entities.stream.StreamEntity

@Dao
interface StreamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStream(stream: StreamEntity)


    @Query("SELECT * FROM stream_table WHERE id = :id")
    suspend fun getStreamById(id: String): StreamEntity?


    @Delete
    suspend fun deleteSavedStream(stream: StreamEntity)

}