package com.chocolate.repository.service.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.users.StoriesLocalDto

interface UserLocalDataSourse {
    suspend fun insertStory(story: StoriesLocalDto)

    suspend fun getStoryById(id: String): StoriesLocalDto?

    suspend fun deleteSavedStory(story: StoriesLocalDto)
}