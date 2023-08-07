package com.chocolate.repository.implementation.secheduled_message

import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.remote.ScheduledMessageDataSource
import repositories.scheduled_message.ScheduledMessageRepository
import javax.inject.Inject

class ScheduledMessageRepositoryImpl @Inject constructor(
    private val scheduledMessageDataSource: ScheduledMessageDataSource
): ScheduledMessageRepository, BaseRepository() {
    override suspend fun getScheduledMessages() {
        return wrapApiCall { scheduledMessageDataSource.getScheduledMessages() }
    }

    override suspend fun createScheduledMessage() {

    }

    override suspend fun editScheduledMessage() {

    }

    override suspend fun deleteScheduledMessage() {

    }
}