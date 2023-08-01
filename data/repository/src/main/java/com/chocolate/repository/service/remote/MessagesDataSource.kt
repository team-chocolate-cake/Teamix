package com.chocolate.repository.service.remote

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

interface MessagesDataSource {

    suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String?,
        localId: String?,
    ): Response<com.chocolate.repository.dto.remote.message.response.SendMessageRemoteDto>

    suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?,
    ): Response<com.chocolate.repository.dto.remote.message.response.SendMessageRemoteDto>

    suspend fun uploadFile(file: MultipartBody.Part): Response<com.chocolate.repository.dto.remote.message.response.FileRemoteDto>

    suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String = "",
        propagateMode: String = "change_one",
        sendNotificationToOldThread: Boolean = false,
        sendNotificationToNewThread: Boolean = true
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    suspend fun deleteMessage(message_id: Int): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean = true,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>? = null,
        clientGravatar: Boolean = true,
        applyMarkdown: Boolean = true
    ): Response<com.chocolate.repository.dto.remote.message.response.MessagesRemoteDto>

    suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    suspend fun renderMessage(
        content: String,
    ): Response<com.chocolate.repository.dto.remote.message.response.RenderMessageRemoteDto>

    suspend fun fetchSingleMessage(
        messageId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.SingleMessageRemoteDto>

    suspend fun checkIfMessagesMatchNarrow(
        msg_ids: String,
        narrow: String
    ): Response<com.chocolate.repository.dto.remote.message.response.MatchNarrowRemoteDto>

    suspend fun getMessagesEditHistory(
        messageId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.MessageEditHistoryRemoteDto>

    suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String,
    ): Response<com.chocolate.repository.dto.remote.message.response.PersonalMessageFlags>

    suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        includeAnchor: Boolean = true,
        narrow: String,
        op: String,
        flag: String
    ): Response<com.chocolate.repository.dto.remote.message.response.PersonalMessageForNarrowRemoteDto>

    suspend fun markAllMessagesAsRead(): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    suspend fun markStreamAsRead(
        steamId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto>

    suspend fun getMessageReadReceipts(
        messageId: Int
    ): Response<com.chocolate.repository.dto.remote.message.response.MessageReadReceiptsRemoteDto>
}
