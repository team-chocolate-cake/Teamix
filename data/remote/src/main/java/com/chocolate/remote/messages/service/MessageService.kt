package com.chocolate.remote.messages.service

import com.chocolate.repository.dto.message.response.DefaultMessageRemoteDto
import com.chocolate.repository.dto.message.response.FileRemoteDto
import com.chocolate.repository.dto.message.response.MatchNarrowRemoteDto
import com.chocolate.repository.dto.message.response.MessageEditHistoryRemoteDto
import com.chocolate.repository.dto.message.response.MessageReadReceiptsRemoteDto
import com.chocolate.repository.dto.message.response.MessagesRemoteDto
import com.chocolate.repository.dto.message.response.PersonalMessageFlagsRemoteDto
import com.chocolate.repository.dto.message.response.PersonalMessageForNarrowRemoteDto
import com.chocolate.repository.dto.message.response.RenderMessageRemoteDto
import com.chocolate.repository.dto.message.response.SendMessageRemoteDto
import com.chocolate.repository.dto.message.response.SingleMessageRemoteDto
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
    ): Response<SendMessageRemoteDto>

    @POST("messages")
    suspend fun sendDirectMessage(
        @Query("type") type: String,
        @Query("to") to: Any,
        @Query("content") content: String,
        @Query("queue_id") queueId: String?,
        @Query("local_id") localId: String?,
    ): Response<SendMessageRemoteDto>

    @Multipart
    @POST("user_uploads")
    suspend fun uploadFile(@Part file: MultipartBody.Part): Response<FileRemoteDto>

    @PATCH("messages/{message_id}")
    suspend fun editMessage(
        @Path("message_id") messageId: Int,
        @Query("content") content: String,
        @Query("topic") topic: String = "",
        @Query("propagate_mode") propagateMode: String = "change_one",
        @Query("send_notification_to_old_thread") sendNotificationToOldThread: Boolean = false,
        @Query("send_notification_to_new_thread") sendNotificationToNewThread: Boolean = true
    ): Response<DefaultMessageRemoteDto>

    @DELETE("messages/{message_id}")
    suspend fun deleteMessage(
        @Query("message_id") messageId: Int
    ): Response<DefaultMessageRemoteDto>

    @GET("messages")
    suspend fun getMessages(
        @Query("anchor") anchor: String?,
        @Query("include_anchor") includeAnchor: Boolean = true,
        @Query("num_before") numBefore: Int,
        @Query("num_after") numAfter: Int,
        @Query("narrow") narrow: List<String>? = null,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("apply_markdown") applyMarkdown: Boolean = true
    ): Response<MessagesRemoteDto>

    @POST("messages/{message_id}/reactions")
    suspend fun addEmojiReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
        @Query("emoji_code") emojiCode: String?,
        @Query("reaction_type") reactionType: String?
    ): Response<DefaultMessageRemoteDto>

    @DELETE("messages/{message_id}/reactions")
    suspend fun deleteEmojiReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
        @Query("emoji_code") emojiCode: String?,
        @Query("reaction_type") reactionType: String?
    ): Response<DefaultMessageRemoteDto>

    @POST("messages/render")
    suspend fun renderMessage(
        @Query("content") content: String,
    ): Response<RenderMessageRemoteDto>

    @GET("messages/{message_id}")
    suspend fun fetchSingleMessage(
        @Path("message_id") messageId: Int
    ): Response<SingleMessageRemoteDto>

    @GET("messages/matches_narrow")
    suspend fun checkIfMessagesMatchNarrow(
        @Query("msg_ids") messageIds: String,
        @Query("narrow") narrow: String
    ): Response<MatchNarrowRemoteDto>

    @GET("messages/{message_id}/history")
    suspend fun getMessagesEditHistory(
        @Path("message_id") messageId: Int
    ): Response<MessageEditHistoryRemoteDto>

    @POST("messages/flags")
    suspend fun updateMessageFlags(
        @Query("messages") messages: List<Int>,
        @Query("op") op: String,
        @Query("flag") flag: String,
    ): Response<PersonalMessageFlagsRemoteDto>

    @POST("messages/flags/narrow")
    suspend fun updatePersonalMessageFlagsForNarrow(
        @Query("anchor") anchor: String,
        @Query("num_before") numBefore: Int,
        @Query("num_after") numAfter: Int,
        @Query("include_anchor") includeAnchor: Boolean = true,
        @Query("narrow") narrow: String,
        @Query("op") op: String,
        @Query("flag") flag: String
    ): Response<PersonalMessageForNarrowRemoteDto>

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
    ): Response<MessageReadReceiptsRemoteDto>

}