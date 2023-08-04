package com.chocolate.remote.messages

import com.chocolate.remote.messages.service.MessageService
import com.chocolate.repository.dto.message.response.DefaultMessageRemoteDto
import com.chocolate.repository.dto.message.response.FileRemoteDto
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
        return messageService.sendStreamMessage(type, to, topic, content, queueId, localId)
    }

    override suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?
    ): Response<SendMessageRemoteDto> {
        return messageService.sendDirectMessage(type, to, content, queueId, localId)
    }

    override suspend fun uploadFile(file: MultipartBody.Part): Response<FileRemoteDto> {
        return messageService.uploadFile(file)
    }

    override suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String,
        propagateMode: String,
        sendNotificationToOldThread: Boolean,
        sendNotificationToNewThread: Boolean
    ): Response<DefaultMessageRemoteDto> {
        return messageService.editMessage(
            messageId,
            content,
            topic,
            propagateMode,
            sendNotificationToOldThread,
            sendNotificationToNewThread
        )
    }

    override suspend fun deleteMessage(message_id: Int): Response<DefaultMessageRemoteDto> {
        return messageService.deleteMessage(message_id)
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
        return messageService.getMessages(
            anchor,
            includeAnchor,
            numBefore,
            numAfter,
            narrow,
            clientGravatar,
            applyMarkdown
        )
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<DefaultMessageRemoteDto> {
        return messageService.addEmojiReaction(messageId, emojiName, emojiCode, reactionType)
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<DefaultMessageRemoteDto> {
        return messageService.deleteEmojiReaction(messageId, emojiName, emojiCode, reactionType)
    }

    override suspend fun renderMessage(content: String): Response<RenderMessageRemoteDto> {
        return messageService.renderMessage(content)
    }

    override suspend fun fetchSingleMessage(messageId: Int): Response<SingleMessageRemoteDto> {
        return messageService.fetchSingleMessage(messageId)
    }

    override suspend fun checkIfMessagesMatchNarrow(
        msg_ids: String,
        narrow: String
    ): Response<MatchNarrowRemoteDto> {
        return messageService.checkIfMessagesMatchNarrow(msg_ids, narrow)
    }

    override suspend fun getMessagesEditHistory(messageId: Int): Response<MessageEditHistoryRemoteDto> {
        return messageService.getMessagesEditHistory(messageId)
    }

    override suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String
    ): Response<PersonalMessageFlags> {
        return messageService.updateMessageFlags(messages, op, flag)
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
        return messageService.updatePersonalMessageFlagsForNarrow(
            anchor,
            numBefore,
            numAfter,
            includeAnchor,
            narrow,
            op,
            flag
        )
    }

    override suspend fun markAllMessagesAsRead(): Response<DefaultMessageRemoteDto> {
        return messageService.markAllMessagesAsRead()
    }

    override suspend fun markStreamAsRead(steamId: Int): Response<DefaultMessageRemoteDto> {
        return messageService.markStreamAsRead(steamId)
    }

    override suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): Response<DefaultMessageRemoteDto> {
        return messageService.markTopicAsRead(steamId, topicName)
    }

    override suspend fun getMessageReadReceipts(messageId: Int): Response<MessageReadReceiptsRemoteDto> {
        return messageService.getMessageReadReceipts(messageId)
    }
}