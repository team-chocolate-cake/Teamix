package com.chocolate.repository.implementation.secheduled_message

import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.ScheduledMessageDataSource
import repositories.scheduled_message.ScheduledMessageRepository
import javax.inject.Inject

class ScheduledMessageRepositoryImpl @Inject constructor(
    private val scheduledMessageDataSource: ScheduledMessageDataSource
): ScheduledMessageRepository, BaseRepository() {
    override suspend fun getScheduledMessages() {
        TODO("Not yet implemented")
    }

    override suspend fun createScheduledMessage() {
        TODO("Not yet implemented")
    }

    override suspend fun editScheduledMessage() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteScheduledMessage() {
        TODO("Not yet implemented")
    }
}