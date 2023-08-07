package com.chocolate.local.dao.user

import com.chocolate.repository.dto.local.users.StoriesLocalDto
import com.chocolate.repository.service.local.UserLocalDataSource
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
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
}