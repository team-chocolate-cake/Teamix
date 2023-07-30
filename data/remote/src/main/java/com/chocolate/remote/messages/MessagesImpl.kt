package com.chocolate.remote.messages

import com.chocolate.remote.messages.service.MessageService
import com.chocolate.repository.dto.message.response.DefaultMessageRemoteDto
import com.chocolate.repository.dto.message.response.FileRemoteDto
import com.chocolate.repository.dto.message.response.MarkAsReadResponse
import com.chocolate.repository.dto.message.response.MatchNarrowRemoteDto
import com.chocolate.repository.dto.message.response.MessageEditHistoryRemoteDto
import com.chocolate.repository.dto.message.response.MessageReadReceiptsRemoteDto
import com.chocolate.repository.dto.message.response.MessagesRemoteDto
import com.chocolate.repository.dto.message.response.PersonalMessageFlags
import com.chocolate.repository.dto.message.response.PersonalMessageForNarrowRemoteDto
import com.chocolate.repository.dto.message.response.RenderMessageRemoteDto
import com.chocolate.repository.dto.message.response.SendMessageRemoteDto
import com.chocolate.repository.dto.message.response.SingleMessageRemoteDto
import com.chocolate.repository.service.MessagesDataSource
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class MessagesImpl @Inject constructor(
    private val messageService: MessageService
): MessagesDataSource {
    override suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String?,
        localId: String?
    ): Response<SendMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?
    ): Response<SendMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadFile(file: MultipartBody.Part): Response<FileRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String,
        propagateMode: String,
        sendNotificationToOldThread: Boolean,
        sendNotificationToNewThread: Boolean
    ): Response<DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMessage(message_id: Int): Response<DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>?,
        clientGravatar: Boolean,
        applyMarkdown: Boolean
    ): Response<MessagesRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun renderMessage(content: String): Response<RenderMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSingleMessage(messageId: Int): Response<SingleMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun checkIfMessagesMatchNarrow(
        msg_ids: String,
        narrow: String
    ): Response<MatchNarrowRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessagesEditHistory(messageId: Int): Response<MessageEditHistoryRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String
    ): Response<PersonalMessageFlags> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        includeAnchor: Boolean,
        narrow: String,
        op: String,
        flag: String
    ): Response<PersonalMessageForNarrowRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun markAllMessagesAsRead(): Response<DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun markStreamAsRead(steamId: Int): Response<MarkAsReadResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): Response<MarkAsReadResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessageReadReceipts(messageId: Int): Response<MessageReadReceiptsRemoteDto> {
        TODO("Not yet implemented")
    }
}