package com.chocolate.repository.repository

import com.chocolate.entities.scheduled_messages.ScheduledMessage
import com.chocolate.repository.mappers.scheduled.toEntity
import com.chocolate.repository.datastore.remote.RemoteDataSource
import repositories.ScheduledMessageRepository
import javax.inject.Inject

class ScheduledMessageRepositoryImpl @Inject constructor(
    private val scheduledMessageRemoteDataSource: RemoteDataSource
) : ScheduledMessageRepository{
    override suspend fun getScheduledMessages(): List<ScheduledMessage> {
        return scheduledMessageRemoteDataSource.getScheduledMessages().scheduledMessages.toEntity()
    }

    override suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Int {
        return scheduledMessageRemoteDataSource.createScheduledMessage(
                type, to, content, topic, scheduledDeliveryTimestamp
            ).scheduledMessageId ?:-1
    }

    override suspend fun editScheduledMessage(
        id: Int,
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Int {
        return scheduledMessageRemoteDataSource.editScheduledMessage(
                id, type, to, content, topic, scheduledDeliveryTimestamp
            ).scheduledMessageId?:-1
    }

    override suspend fun deleteScheduledMessage(id: Int) {
         scheduledMessageRemoteDataSource.deleteScheduledMessage(id)
    }
}