package com.chocolate.local.dao.user

import com.chocolate.repository.model.localDto.users.StoriesLocalDto
import com.chocolate.repository.model.localDto.users.UserLocalDto
import com.chocolate.repository.service.local.UserLocalDataSource
import javax.inject.Inject

class UserRoomDataSource @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {
    override suspend fun insertStory(story: StoriesLocalDto) {
        userDao.upsertStory(story)
    }

    override suspend fun getStoryById(id: String): StoriesLocalDto? {
        return userDao.getStoryById(id)
    }

    override suspend fun deleteSavedStory(storyId: Int) {
        userDao.deleteSavedStory(storyId)
    }

    override suspend fun upsertUserData(userLocalDto: UserLocalDto) {
        userDao.upsertUser(userLocalDto)
    }

    override suspend fun getCurrentUserData(): UserLocalDto {
        return userDao.getCurrentUserData()
    }
}