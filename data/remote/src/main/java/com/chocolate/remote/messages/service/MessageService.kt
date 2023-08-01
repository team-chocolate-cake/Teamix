package com.chocolate.remote.messages.service

import com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto
import com.chocolate.repository.dto.remote.message.response.FileRemoteDto
import com.chocolate.repository.dto.remote.message.response.MatchNarrowRemoteDto
import com.chocolate.repository.dto.remote.message.response.MessageEditHistoryRemoteDto
import com.chocolate.repository.dto.remote.message.response.MessageReadReceiptsRemoteDto
import com.chocolate.repository.dto.remote.message.response.MessagesRemoteDto
import com.chocolate.repository.dto.remote.message.response.PersonalMessageFlags
import com.chocolate.repository.dto.remote.message.response.PersonalMessageForNarrowRemoteDto
import com.chocolate.repository.dto.remote.message.response.RenderMessageRemoteDto
import com.chocolate.repository.dto.remote.message.response.SendMessageRemoteDto
import com.chocolate.repository.dto.remote.message.response.SingleMessageRemoteDto
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
        @Query("to") to: Any,
        @Query("topic") topic: String,
        @Query("content") content: String,
        @Query("queue_id") queueId: String?,
        @Query("local_id") localId: String?,
    ): Response<com.chocolate.repository.dto.remote.message.response.SendMessageRemoteDto>

    @POST("messages")
    suspend fun sendDirectMessage(
        @Query("type") type: String,
        @Query("to") to: Any,
        @Query("content") content: String,
        @Query("queue_id") queueId: String?,
        @Query("local_id") localId: String?,
    ): Response<com.chocolate.repository.dto.remote.message.response.SendMessageRemoteDto>

    @Multipart
    @POST("user_uploads")
    suspend fun uploadFile(@Part file: MultipartBody.Part): Response<com.chocolate.repository.dto.remote.message.response.FileRemoteDto>

    @PATCH("messages/{message_id}")
    suspend fun editMessage(
        @Path("message_id") messageId: Int,
        @Query("content") content: String,
        @Query("topic") topic: String = "",
        @Query("propagate_mode") propagateMode: String = "change_one",
        @Query("send_notification_to_old_thread") sendNotificationToOldThread: Boolean = false,
        @Query("send_notification_to_new_thread") sendNotificationToNewThread: Boolean = true
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    @DELETE("messages/{message_id}")
    suspend fun deleteMessage(
        @Query("message_id") messageId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    @GET("messages")
    suspend fun getMessages(
        @Query("anchor") anchor: String?,
        @Query("include_anchor") includeAnchor: Boolean = true,
        @Query("num_before") numBefore: Int,
        @Query("num_after") numAfter: Int,
        @Query("narrow") narrow: List<String>? = null,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("apply_markdown") applyMarkdown: Boolean = true
    ): Response<com.chocolate.repository.dto.remote.message.response.MessagesRemoteDto>

    @POST("messages/{message_id}/reactions")
    suspend fun addEmojiReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
        @Query("emoji_code") emojiCode: String?,
        @Query("reaction_type") reactionType: String?
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    @DELETE("messages/{message_id}/reactions")
    suspend fun deleteEmojiReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
        @Query("emoji_code") emojiCode: String?,
        @Query("reaction_type") reactionType: String?
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    @POST("messages/render")
    suspend fun renderMessage(
        @Query("content") content: String,
    ): Response<com.chocolate.repository.dto.remote.message.response.RenderMessageRemoteDto>

    @GET("messages/{message_id}")
    suspend fun fetchSingleMessage(
        @Path("message_id") messageId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.SingleMessageRemoteDto>

    @GET("messages/matches_narrow")
    suspend fun checkIfMessagesMatchNarrow(
        @Query("msg_ids") messageIds: String,
        @Query("narrow") narrow: String
    ): Response<com.chocolate.repository.dto.remote.message.response.MatchNarrowRemoteDto>

    @GET("messages/{message_id}/history")
    suspend fun getMessagesEditHistory(
        @Path("message_id") messageId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.MessageEditHistoryRemoteDto>

    @POST("messages/flags")
    suspend fun updateMessageFlags(
        @Query("messages") messages: List<Int>,
        @Query("op") op: String,
        @Query("flag") flag: String,
    ): Response<com.chocolate.repository.dto.remote.message.response.PersonalMessageFlags>

    @POST("messages/flags/narrow")
    suspend fun updatePersonalMessageFlagsForNarrow(
        @Query("anchor") anchor: String,
        @Query("num_before") numBefore: Int,
        @Query("num_after") numAfter: Int,
        @Query("include_anchor") includeAnchor: Boolean = true,
        @Query("narrow") narrow: String,
        @Query("op") op: String,
        @Query("flag") flag: String
    ): Response<com.chocolate.repository.dto.remote.message.response.PersonalMessageForNarrowRemoteDto>

    @POST("mark_all_as_read")
    suspend fun markAllMessagesAsRead(): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    @POST("mark_stream_as_read")
    suspend fun markStreamAsRead(
        @Query("stream_id") steamId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    @POST("mark_topic_as_read")
    suspend fun markTopicAsRead(
        @Query("stream_id") steamId: Int,
        @Query("topic_name") topicName: String
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    @GET("messages/{message_id}/read_receipts")
    suspend fun getMessageReadReceipts(
        @Path("message_id") messageId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.MessageReadReceiptsRemoteDto>

}