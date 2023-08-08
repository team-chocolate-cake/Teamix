package com.chocolate.repository.service.remote

import com.chocolate.repository.dto.remote.scheduled_message.response.BaseScheduledMessageResponse
import com.chocolate.repository.dto.remote.scheduled_message.response.ScheduledMessagesDto
import retrofit2.Response

interface ScheduledMessageDataSource {

    suspend fun getScheduledMessages(): Response<ScheduledMessagesDto>

    suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Response<BaseScheduledMessageResponse>

    suspend fun editScheduledMessage(
        id: Int,
        type: String? = null,
        to: Any? = null,
        content: String? = null,
        topic: String? = null,
        scheduledDeliveryTimestamp: Long? = null
    ): Response<BaseScheduledMessageResponse>

    suspend fun deleteScheduledMessage(id: Int): Response<BaseScheduledMessageResponse>
}
