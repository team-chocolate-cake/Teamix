package com.chocolate.repository.service

import com.chocolate.repository.dto.message.response.DefaultMessageDto
import com.chocolate.repository.dto.message.response.FileDto
import com.chocolate.repository.dto.message.response.MatchNarrowDto
import com.chocolate.repository.dto.message.response.MessageEditHistoryDto
import com.chocolate.repository.dto.message.response.MessageReadReceiptsDto
import com.chocolate.repository.dto.message.response.MessagesDto
import com.chocolate.repository.dto.message.response.PersonalMessageFlagsDto
import com.chocolate.repository.dto.message.response.PersonalMessageForNarrowDto
import com.chocolate.repository.dto.message.response.RenderMessageDto
import com.chocolate.repository.dto.message.response.SendMessageDto
import com.chocolate.repository.dto.message.response.SingleMessageDto
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
    ): Response<SendMessageDto>

    suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String? = null,
        localId: String? = null,
    ): Response<SendMessageDto>

    suspend fun uploadFile(file: MultipartBody.Part): Response<FileDto>

    suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String = "",
        propagateMode: String = "change_one",
        sendNotificationToOldThread: Boolean = false,
        sendNotificationToNewThread: Boolean = true
    ): Response<DefaultMessageDto>

    suspend fun deleteMessage(messageId: Int): Response<DefaultMessageDto>

    suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean = true,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>? = null,
        clientGravatar: Boolean = true,
        applyMarkdown: Boolean = true
    ): Response<MessagesDto>

    suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?= null,
        reactionType: String? = null
    ): Response<DefaultMessageDto>

    suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String? = null,
        reactionType: String? = null
    ): Response<DefaultMessageDto>

    suspend fun renderMessage(
        content: String,
    ): Response<RenderMessageDto>

    suspend fun fetchSingleMessage(
        messageId: Int
    ): Response<SingleMessageDto>

    suspend fun checkIfMessagesMatchNarrow(
        msg_ids: String,
        narrow: String
    ): Response<MatchNarrowDto>

    suspend fun getMessagesEditHistory(
        messageId: Int
    ): Response<MessageEditHistoryDto>

    suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String,
    ): Response<PersonalMessageFlagsDto>

    suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        includeAnchor: Boolean = true,
        narrow: String,
        op: String,
        flag: String
    ): Response<PersonalMessageForNarrowDto>

    suspend fun markAllMessagesAsRead(): Response<DefaultMessageDto>

    suspend fun markStreamAsRead(
        steamId: Int
    ): Response<DefaultMessageDto>

    suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): Response<DefaultMessageDto>

    suspend fun getMessageReadReceipts(
        messageId: Int
    ): Response<MessageReadReceiptsDto>
}
