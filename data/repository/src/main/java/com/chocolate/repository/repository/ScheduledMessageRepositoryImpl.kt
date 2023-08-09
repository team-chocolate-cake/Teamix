package com.chocolate.repository.repository

import com.chocolate.entities.scheduled_messages.BaseScheduledMessage
import com.chocolate.entities.scheduled_messages.ScheduledMessages
import com.chocolate.repository.mappers.scheduled.toBaseScheduledMessage
import com.chocolate.repository.mappers.scheduled.toScheduledMessages
import com.chocolate.repository.service.remote.ScheduledMessageRemoteDataSource
import repositories.ScheduledMessageRepository
import javax.inject.Inject

class ScheduledMessageRepositoryImpl @Inject constructor(
    private val scheduledMessageRemoteDataSource: ScheduledMessageRemoteDataSource
) : ScheduledMessageRepository, BaseRepository() {
    override suspend fun getScheduledMessages(): ScheduledMessages {
        return wrapApiCall { scheduledMessageRemoteDataSource.getScheduledMessages() }.toScheduledMessages()
    }

    override suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): BaseScheduledMessage {
        return wrapApiCall {
            scheduledMessageRemoteDataSource.createScheduledMessage(
                type, to, content, topic, scheduledDeliveryTimestamp
            )
        }.toBaseScheduledMessage()
    }

    override suspend fun editScheduledMessage(
        id: Int,
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): BaseScheduledMessage {
        return wrapApiCall {
            scheduledMessageRemoteDataSource.editScheduledMessage(
                id, type, to, content, topic, scheduledDeliveryTimestamp
            )
        }.toBaseScheduledMessage()
    }

    override suspend fun deleteScheduledMessage(id: Int): BaseScheduledMessage {
        return wrapApiCall { scheduledMessageRemoteDataSource.deleteScheduledMessage(id) }.toBaseScheduledMessage()
    }
}