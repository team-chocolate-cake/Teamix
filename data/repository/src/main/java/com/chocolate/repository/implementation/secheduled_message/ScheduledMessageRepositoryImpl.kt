package com.chocolate.repository.implementation.secheduled_message

import com.chocolate.entities.scheduled_messages.BaseScheduledMessage
import com.chocolate.entities.scheduled_messages.ScheduledMessages
import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.mappers.scheduled.toBaseScheduledMessage
import com.chocolate.repository.mappers.scheduled.toScheduledMessages
import com.chocolate.repository.service.remote.ScheduledMessageDataSource
import repositories.scheduled_message.ScheduledMessageRepository
import javax.inject.Inject

class ScheduledMessageRepositoryImpl @Inject constructor(
    private val scheduledMessageDataSource: ScheduledMessageDataSource
) : ScheduledMessageRepository, BaseRepository() {
    override suspend fun getScheduledMessages(): ScheduledMessages {
        return wrapApiCall { scheduledMessageDataSource.getScheduledMessages() }.toScheduledMessages()
    }

    override suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): BaseScheduledMessage {
        return wrapApiCall {
            scheduledMessageDataSource.createScheduledMessage(
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
            scheduledMessageDataSource.editScheduledMessage(
                id, type, to, content, topic, scheduledDeliveryTimestamp
            )
        }.toBaseScheduledMessage()
    }

    override suspend fun deleteScheduledMessage(id: Int): BaseScheduledMessage {
        return wrapApiCall { scheduledMessageDataSource.deleteScheduledMessage(id) }.toBaseScheduledMessage()
    }
}