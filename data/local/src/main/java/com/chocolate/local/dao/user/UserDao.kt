package com.chocolate.local.dao.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.users.StoriesLocalDto

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: StoriesLocalDto)

    @Query("SELECT * FROM story_table WHERE id = :id")
    suspend fun getStoryById(id: String): StoriesLocalDto?

    @Delete
    suspend fun deleteSavedStory(story: StoriesLocalDto)

}