package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.chocolate.repository.model.localDto.stream.StreamLocalDto
import com.chocolate.repository.model.localDto.users.UserLocalDto

@Dao
interface TeamixDao {
    @Upsert
    suspend fun upsertStream(stream: StreamLocalDto)

    @Query("SELECT * FROM stream_table WHERE id = :id")
    suspend fun getStreamById(id: String): StreamLocalDto?

    @Query("DELETE FROM stream_table WHERE id = :streamId")
    suspend fun deleteStream(streamId: Int)

    @Upsert
    suspend fun upsertUser(userLocalDto: UserLocalDto)

    @Query("SELECT * FROM table_current_user")
    suspend fun getCurrentUserData(): UserLocalDto

}