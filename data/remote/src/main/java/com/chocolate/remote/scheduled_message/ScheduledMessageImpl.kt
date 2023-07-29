package com.chocolate.remote.scheduled_message

import com.chocolate.remote.scheduled_message.service.ScheduledMessageService
import com.chocolate.repository.dto.scheduled_message.response.BaseScheduledMessageResponse
import com.chocolate.repository.dto.scheduled_message.response.ScheduledMessagesDto
import com.chocolate.repository.service.IScheduledMessageService
import retrofit2.Response

class ScheduledMessageImpl(
    private val scheduledMessageService: ScheduledMessageService
): IScheduledMessageService {
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