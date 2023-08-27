package com.chocolate.remote.api

import com.chocolate.repository.model.dto.message.response.DefaultMessageRemoteDto
import com.chocolate.repository.model.dto.message.response.FileRemoteDto
import com.chocolate.repository.model.dto.message.response.MatchNarrowDto
import com.chocolate.repository.model.dto.message.response.MessageEditHistoryDto
import com.chocolate.repository.model.dto.message.response.MessageReadReceiptsDto
import com.chocolate.repository.model.dto.message.response.MessagesRemoteDto
import com.chocolate.repository.model.dto.message.response.RenderMessageDto
import com.chocolate.repository.model.dto.message.response.SendMessageDto
import com.chocolate.repository.model.dto.message.response.SingleMessageDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MessageService {

    @POST("messages")
    suspend fun sendStreamMessage(
        @Query("type") type: String,
        @Query("to") recipients: Any,
        @Query("topic") topic: String,
        @Query("content") content: String
    ): Response<SendMessageDto>

    @POST("messages")
    suspend fun sendDirectMessage(
        @Query("type") type: String,
        @Query("to") recipients: String,
        @Query("content") content: String
    ): Response<SendMessageDto>

    @Multipart
    @POST("user_uploads")
    suspend fun uploadFile(@Part file: MultipartBody.Part): Response<FileRemoteDto>

    @PATCH("messages/{message_id}")
    suspend fun editMessage(
        @Path("message_id") messageId: Int,
        @Query("content") content: String,
        @Query("topic") topic: String = "",
    ): Response<DefaultMessageRemoteDto>

    @DELETE("messages/{message_id}")
    suspend fun deleteMessage(
        @Query("message_id") messageId: Int
    ): Response<DefaultMessageRemoteDto>

    @GET("messages")
    suspend fun getMessages(
        @Query("num_before") numBefore: Int,
        @Query("num_after") numAfter: Int,
    ): Response<MessagesRemoteDto>

    @POST("messages/{message_id}/reactions")
    suspend fun addEmojiReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
    ): Response<DefaultMessageRemoteDto>

    @DELETE("messages/{message_id}/reactions")
    suspend fun deleteEmojiReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
    ): Response<DefaultMessageRemoteDto>

    @POST("messages/render")
    suspend fun renderMessage(
        @Query("content") content: String,
    ): Response<RenderMessageDto>

    @GET("messages/{message_id}")
    suspend fun fetchSingleMessage(
        @Path("message_id") messageId: Int
    ): Response<SingleMessageDto>

    @GET("messages/matches_narrow")
    suspend fun checkIfMessagesMatchNarrow(
        @Query("msg_ids") messageIds: String,
        @Query("narrow") narrow: String
    ): Response<MatchNarrowDto>

    @GET("messages/{message_id}/history")
    suspend fun getMessagesEditHistory(
        @Path("message_id") messageId: Int
    ): Response<MessageEditHistoryDto>

    @POST("mark_all_as_read")
    suspend fun markAllMessagesAsRead(): Response<DefaultMessageRemoteDto>

    @POST("mark_stream_as_read")
    suspend fun markStreamAsRead(
        @Query("stream_id") steamId: Int
    ): Response<DefaultMessageRemoteDto>

    @POST("mark_topic_as_read")
    suspend fun markTopicAsRead(
        @Query("stream_id") steamId: Int,
        @Query("topic_name") topicName: String
    ): Response<DefaultMessageRemoteDto>

    @GET("messages/{message_id}/read_receipts")
    suspend fun getMessageReadReceipts(
        @Path("message_id") messageId: Int
    ): Response<MessageReadReceiptsDto>

}