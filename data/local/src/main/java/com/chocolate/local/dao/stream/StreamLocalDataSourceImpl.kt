package com.chocolate.local.dao.stream

import com.chocolate.repository.dto.local.stream.StreamLocalDto
import com.chocolate.repository.service.local.StreamLocalDataSource
import javax.inject.Inject

class StreamLocalDataSourceImpl @Inject constructor(
    private val streamDao: StreamDao
) :StreamLocalDataSource {
    override suspend fun insertStream(stream: StreamLocalDto) {
        streamDao.upsertStream(stream)
    }

    override suspend fun getStreamById(id: String): StreamLocalDto? {
        return streamDao.getStreamById(id)
    }

    override suspend fun deleteStream(streamId: Int) {
        streamDao.deleteStream(streamId)
    }
}