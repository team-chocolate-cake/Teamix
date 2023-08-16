package com.chocolate.local.dao.stream

import com.chocolate.repository.model.localDto.stream.StreamLocalDto
import com.chocolate.repository.service.local.StreamRoomDataSource
import javax.inject.Inject

class StreamLocalDataSource @Inject constructor(
    private val streamDao: StreamDao
) :StreamRoomDataSource {
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