package com.chocolate.local.dao.stream

import com.chocolate.local.dao.draft.DraftMessagesDao
import com.chocolate.repository.dto.local.stream.StreamLocalDto
import com.chocolate.repository.service.local.StreamLocalDataSource
import javax.inject.Inject

class StreamLocalDataSourceImpl @Inject constructor(
    private val streamDao: StreamDao
) :StreamLocalDataSource {
    override suspend fun insertStream(stream: StreamLocalDto) {
        streamDao.insertStream(stream)
    }

    override suspend fun getStreamById(id: String): StreamLocalDto? {
        return streamDao.getStreamById(id)
    }

    override suspend fun deleteSavedStream(stream: StreamLocalDto) {
        streamDao.deleteSavedStream(stream)
    }
}