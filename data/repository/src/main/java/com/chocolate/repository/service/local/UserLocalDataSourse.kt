package com.chocolate.repository.service.local

import com.chocolate.repository.model.localDto.users.StoriesLocalDto

interface UserRoomDataSource {
    suspend fun insertStory(story: StoriesLocalDto)

    suspend fun getStoryById(id: String): StoriesLocalDto?

    suspend fun deleteSavedStory(storyId: Int)
}