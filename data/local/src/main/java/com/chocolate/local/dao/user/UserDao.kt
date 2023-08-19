package com.chocolate.local.dao.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.chocolate.repository.model.localDto.users.StoriesLocalDto
import com.chocolate.repository.model.localDto.users.UserLocalDto

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(userLocalDto: UserLocalDto)

    @Query("SELECT * FROM table_current_user")
    suspend fun getCurrentUserData(): UserLocalDto

    @Upsert
    suspend fun upsertStory(story: StoriesLocalDto)

    @Query("SELECT * FROM story_table WHERE id = :id")
    suspend fun getStoryById(id: String): StoriesLocalDto?

    @Query("DELETE FROM story_table WHERE id = :storyId")
    suspend fun deleteSavedStory(storyId: Int)

}