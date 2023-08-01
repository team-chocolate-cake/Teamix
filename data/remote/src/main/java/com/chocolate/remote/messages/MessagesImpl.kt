package com.chocolate.remote.messages

import com.chocolate.remote.messages.service.MessageService
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
import com.chocolate.repository.service.remote.MessagesDataSource
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
    ): Response<com.chocolate.repository.dto.remote.message.response.SendMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?
    ): Response<com.chocolate.repository.dto.remote.message.response.SendMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadFile(file: MultipartBody.Part): Response<com.chocolate.repository.dto.remote.message.response.FileRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String,
        propagateMode: String,
        sendNotificationToOldThread: Boolean,
        sendNotificationToNewThread: Boolean
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMessage(message_id: Int): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto> {
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
    ): Response<com.chocolate.repository.dto.remote.message.response.MessagesRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun renderMessage(content: String): Response<com.chocolate.repository.dto.remote.message.response.RenderMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSingleMessage(messageId: Int): Response<com.chocolate.repository.dto.remote.message.response.SingleMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun checkIfMessagesMatchNarrow(
        msg_ids: String,
        narrow: String
    ): Response<com.chocolate.repository.dto.remote.message.response.MatchNarrowRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessagesEditHistory(messageId: Int): Response<com.chocolate.repository.dto.remote.message.response.MessageEditHistoryRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String
    ): Response<com.chocolate.repository.dto.remote.message.response.PersonalMessageFlags> {
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
    ): Response<com.chocolate.repository.dto.remote.message.response.PersonalMessageForNarrowRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun markAllMessagesAsRead(): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun markStreamAsRead(steamId: Int): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): Response<com.chocolate.repository.dto.remote.message.response.DefaultMessageRemoteDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessageReadReceipts(messageId: Int): Response<com.chocolate.repository.dto.remote.message.response.MessageReadReceiptsRemoteDto> {
        TODO("Not yet implemented")
    }
}