package com.chocolate.local.dao.user

import com.chocolate.repository.model.localDto.users.StoriesLocalDto
import com.chocolate.repository.service.local.UserRoomDataSource
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDao: UserDao
) : UserRoomDataSource {
    override suspend fun insertStory(story: StoriesLocalDto) {
        userDao.upsertStory(story)
    }

    override suspend fun getStoryById(id: String): StoriesLocalDto? {
        return userDao.getStoryById(id)
    }

    override suspend fun deleteSavedStory(storyId: Int) {
        userDao.deleteSavedStory(storyId)
    }
}