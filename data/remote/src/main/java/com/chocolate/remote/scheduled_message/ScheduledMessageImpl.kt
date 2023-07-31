package com.chocolate.remote.scheduled_message

import com.chocolate.remote.scheduled_message.service.ScheduledMessageService
import com.chocolate.repository.dto.scheduled_message.response.BaseScheduledMessageResponse
import com.chocolate.repository.dto.scheduled_message.response.ScheduledMessagesDto
import com.chocolate.repository.service.ScheduledMessageDataSource
import retrofit2.Response
import javax.inject.Inject

class ScheduledMessageImpl @Inject constructor(
    private val scheduledMessageService: ScheduledMessageService
): ScheduledMessageDataSource {
    override suspend fun getScheduledMessages(): Response<ScheduledMessagesDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Response<BaseScheduledMessageResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun editScheduledMessage(
        id: Int,
        type: String?,
        to: Any?,
        content: String?,
        topic: String?,
        scheduledDeliveryTimestamp: Long?
    ): Response<BaseScheduledMessageResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteScheduledMessage(id: Int): Response<BaseScheduledMessageResponse> {
        TODO("Not yet implemented")
    }
}