package com.chocolate.remote.messages

import com.chocolate.remote.messages.service.MessageService
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
    ): Response<SendMessageDto> {
        return messageService.sendStreamMessage(type, to, topic, content, queueId, localId)
    }

    override suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?
    ): Response<SendMessageDto> {
        return messageService.sendDirectMessage(type, to, content, queueId, localId)
    }

    override suspend fun uploadFile(file: MultipartBody.Part): Response<FileDto> {
        return messageService.uploadFile(file)
    }

    override suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String,
        propagateMode: String,
        sendNotificationToOldThread: Boolean,
        sendNotificationToNewThread: Boolean
    ): Response<DefaultMessageDto> {
        return messageService.editMessage(
            messageId,
            content,
            topic,
            propagateMode,
            sendNotificationToOldThread,
            sendNotificationToNewThread
        )
    }

    override suspend fun deleteMessage(messageId: Int): Response<DefaultMessageDto> {
        return messageService.deleteMessage(messageId)
    }

    override suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>?,
        clientGravatar: Boolean,
        applyMarkdown: Boolean
    ): Response<MessagesDto> {
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
    ): Response<DefaultMessageDto> {
        return messageService.addEmojiReaction(messageId, emojiName, emojiCode, reactionType)
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Response<DefaultMessageDto> {
        return messageService.deleteEmojiReaction(messageId, emojiName, emojiCode, reactionType)
    }

    override suspend fun renderMessage(content: String): Response<RenderMessageDto> {
        return messageService.renderMessage(content)
    }

    override suspend fun fetchSingleMessage(messageId: Int): Response<SingleMessageDto> {
        return messageService.fetchSingleMessage(messageId)
    }

    override suspend fun checkIfMessagesMatchNarrow(
        msg_ids: String,
        narrow: String
    ): Response<MatchNarrowDto> {
        return messageService.checkIfMessagesMatchNarrow(msg_ids, narrow)
    }

    override suspend fun getMessagesEditHistory(messageId: Int): Response<MessageEditHistoryDto> {
        return messageService.getMessagesEditHistory(messageId)
    }

    override suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String
    ): Response<PersonalMessageFlagsDto> {
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
    ): Response<PersonalMessageForNarrowDto> {
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

    override suspend fun markAllMessagesAsRead(): Response<DefaultMessageDto> {
        return messageService.markAllMessagesAsRead()
    }

    override suspend fun markStreamAsRead(steamId: Int): Response<DefaultMessageDto> {
        return messageService.markStreamAsRead(steamId)
    }

    override suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): Response<DefaultMessageDto> {
        return messageService.markTopicAsRead(steamId, topicName)
    }

    override suspend fun getMessageReadReceipts(messageId: Int): Response<MessageReadReceiptsDto> {
        return messageService.getMessageReadReceipts(messageId)
    }
}