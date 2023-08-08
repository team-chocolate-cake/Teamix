package com.chocolate.remote.scheduled_message.service

import com.chocolate.repository.dto.remote.scheduled_message.response.BaseScheduledMessageResponse
import com.chocolate.repository.dto.remote.scheduled_message.response.ScheduledMessagesDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduledMessageService {

    @GET("scheduled_messages")
    suspend fun getScheduledMessages(): Response<ScheduledMessagesDto>

    @POST("scheduled_messages")
    suspend fun createScheduledMessage(
        @Query("type") type: String,
        @Query("to") to: Any,
        @Query("content") content: String,
        @Query("topic") topic: String,
        @Query("scheduled_delivery_timestamp") scheduledDeliveryTimestamp: Long,
    ): Response<BaseScheduledMessageResponse>

    @PATCH("scheduled_messages/{scheduled_message_id}")
    suspend fun editScheduledMessage(
        @Path("scheduled_message_id") id: Int,
        @Query("type") type: String? = null,
        @Query("to") to: Any? = null,
        @Query("content") content: String? = null,
        @Query("topic") topic: String? = null,
        @Query("scheduled_delivery_timestamp") scheduledDeliveryTimestamp: Long? = null,
        ):Response<BaseScheduledMessageResponse>

    @DELETE("scheduled_messages/{scheduled_message_id}")
    suspend fun deleteScheduledMessage(@Path("scheduled_message_id") id: Int):
            Response<BaseScheduledMessageResponse>

}