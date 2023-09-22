package com.chocolate.local.dao

import com.chocolate.local.database.TeamixDatabase
import com.chocolate.repository.datastore.local.LocalDataSource
import com.chocolate.repository.model.localDto.stream.StreamLocalDto
import com.chocolate.repository.model.localDto.users.UserLocalDto
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val teamixDao: TeamixDao,
    private val teamixDatabase: TeamixDatabase
) : LocalDataSource {

    override suspend fun insertStream(stream: StreamLocalDto) {
        teamixDao.upsertStream(stream)
    }

    override suspend fun getStreamById(id: String): StreamLocalDto? {
        return teamixDao.getStreamById(id)
    }

    override suspend fun deleteStream(streamId: Int) {
        teamixDao.deleteStream(streamId)
    }

    override suspend fun deleteDataBase() {
        teamixDatabase.clearAllTables()
    }

    override suspend fun upsertUserData(userLocalDto: UserLocalDto) {
        teamixDao.upsertUser(userLocalDto)
    }

    override suspend fun getCurrentUserData(): UserLocalDto {
        return teamixDao.getCurrentUserData()
    }
}