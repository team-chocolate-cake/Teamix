package com.chocolate.local.dao.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.chocolate.repository.dto.local.users.StoriesLocalDto

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertStory(story: StoriesLocalDto)

    @Query("SELECT * FROM story_table WHERE id = :id")
    suspend fun getStoryById(id: String): StoriesLocalDto?

    @Query("DELETE FROM story_table WHERE id = :storyId")
    suspend fun deleteSavedStory(storyId: Int)

}