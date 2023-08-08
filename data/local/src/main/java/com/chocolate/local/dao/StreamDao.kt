package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.stream.StreamEntity

@Dao
interface StreamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStream(stream: StreamEntity)


    @Query("SELECT * FROM stream_table WHERE id = :id")
    fun getStreamById(id: String): StreamEntity?


    @Delete
    fun deleteSavedStream(stream: StreamEntity)

}