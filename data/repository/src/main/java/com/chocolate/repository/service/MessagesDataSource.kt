package com.chocolate.repository.service

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

interface MessagesDataSource {

    suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String? = null,
        localId: String? = null,
    ): Response<SendMessageRemoteDto>

    suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String? = null,
        localId: String? = null,
    ): Response<SendMessageRemoteDto>

    suspend fun uploadFile(file: MultipartBody.Part): Response<FileRemoteDto>

    suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String = "",
        propagateMode: String = "change_one",
        sendNotificationToOldThread: Boolean = false,
        sendNotificationToNewThread: Boolean = true
    ): Response<DefaultMessageRemoteDto>

    suspend fun deleteMessage(messageId: Int): Response<DefaultMessageRemoteDto>

    suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean = true,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>? = null,
        clientGravatar: Boolean = true,
        applyMarkdown: Boolean = true
    ): Response<MessagesRemoteDto>

    suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?= null,
        reactionType: String? = null
    ): Response<DefaultMessageRemoteDto>

    suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String? = null,
        reactionType: String? = null
    ): Response<DefaultMessageRemoteDto>

    suspend fun renderMessage(
        content: String,
    ): Response<RenderMessageRemoteDto>

    suspend fun fetchSingleMessage(
        messageId: Int
    ): Response<SingleMessageRemoteDto>

    suspend fun checkIfMessagesMatchNarrow(
        msg_ids: String,
        narrow: String
    ): Response<MatchNarrowRemoteDto>

    suspend fun getMessagesEditHistory(
        messageId: Int
    ): Response<MessageEditHistoryRemoteDto>

    suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String,
    ): Response<PersonalMessageFlagsRemoteDto>

    suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        includeAnchor: Boolean = true,
        narrow: String,
        op: String,
        flag: String
    ): Response<PersonalMessageForNarrowRemoteDto>

    suspend fun markAllMessagesAsRead(): Response<DefaultMessageRemoteDto>

    suspend fun markStreamAsRead(
        steamId: Int
    ): Response<DefaultMessageRemoteDto>

    suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): Response<DefaultMessageRemoteDto>

    suspend fun getMessageReadReceipts(
        messageId: Int
    ): Response<MessageReadReceiptsRemoteDto>
}
