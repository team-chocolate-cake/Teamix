package com.chocolate.repository.service.local

import com.chocolate.repository.model.localDto.users.StoriesLocalDto
import com.chocolate.repository.model.localDto.users.UserLocalDto

interface UserLocalDataSource {
    suspend fun insertStory(story: StoriesLocalDto)

    suspend fun getStoryById(id: String): StoriesLocalDto?

    suspend fun deleteSavedStory(storyId: Int)
    
    suspend fun upsertUserData(userLocalDto: UserLocalDto)
    
    suspend fun getCurrentUserData(): UserLocalDto?
}