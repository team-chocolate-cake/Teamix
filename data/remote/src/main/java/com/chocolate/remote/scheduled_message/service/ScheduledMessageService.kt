package com.chocolate.remote.scheduled_message.service

import com.chocolate.remote.scheduled_message.dto.ScheduledMessagesDto
import com.chocolate.remote.scheduled_message.response.BaseScheduledMessageResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduledMessageService {

    @GET("scheduled_messages")
    fun getScheduledMessages(): ScheduledMessagesDto

    @POST("scheduled_messages")
    fun createScheduledMessage(
        @Query("type") type: String,
        @Query("to") to: List<Int>,
        @Query("content") content : String,
        @Query("scheduled_delivery_timestamp") scheduledDeliveryTimestamp : Int,
    ): BaseScheduledMessageResponse

    @PATCH("scheduled_messages")
    fun editScheduledMessage(@Path("scheduled_message_id ") id: Int): BaseScheduledMessageResponse

    @DELETE("scheduled_messages")
    fun deleteScheduledMessage(@Path("scheduled_message_id ") id: Int): BaseScheduledMessageResponse

}