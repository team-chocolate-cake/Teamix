package com.chocolate.repository.service.local

import com.chocolate.repository.dto.local.users.StoriesLocalDto

interface UserLocalDataSource {
    suspend fun insertStory(story: StoriesLocalDto)

    suspend fun getStoryById(id: String): StoriesLocalDto?

    suspend fun deleteSavedStory(story: StoriesLocalDto)
}