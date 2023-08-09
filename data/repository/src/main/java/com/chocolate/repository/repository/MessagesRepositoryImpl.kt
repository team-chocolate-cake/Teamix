package com.chocolate.repository.repository

import com.chocolate.entities.messages.AttachmentMessage
import com.chocolate.entities.messages.MatchNarrow
import com.chocolate.entities.messages.MessageEditHistory
import com.chocolate.entities.messages.MessageReadReceipts
import com.chocolate.entities.messages.Messages
import com.chocolate.entities.messages.PersonalMessage
import com.chocolate.entities.messages.PersonalMessageForNarrow
import com.chocolate.entities.messages.RenderMessage
import com.chocolate.entities.messages.SendMessage
import com.chocolate.entities.messages.SingleMessage
import com.chocolate.repository.mappers.messages.toMessages
import com.chocolate.repository.mappers.messages.toSingleMessage
import com.chocolate.repository.mappers.messages.toSendMessage
import com.chocolate.repository.mappers.messages.toRenderMessage
import com.chocolate.repository.mappers.messages.toPersonalMessage
import com.chocolate.repository.mappers.messages.toMessageReadReceipts
import com.chocolate.repository.mappers.messages.toMessageEditHistory
import com.chocolate.repository.mappers.messages.toMatchNarrow
import com.chocolate.repository.mappers.messages.toAttachmentMessage
import com.chocolate.repository.mappers.messages.toPersonalMessageForNarrow
import com.chocolate.repository.service.remote.MessagesRemoteDataSource
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import repositories.MessagesRepository
import java.io.File
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val messageDataSource: MessagesRemoteDataSource
) : MessagesRepository, BaseRepository() {
    override suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String?,
        localId: String?
    ): SendMessage {
        val sendSteamMessageDto = wrapApiCall {
            messageDataSource.sendStreamMessage(
                type,
                to,
                topic,
                content,
                queueId,
                localId
            )
        }
        return sendSteamMessageDto.toSendMessage()
    }

    override suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?
    ): SendMessage {
        val sendDirectMessageDto =
            wrapApiCall { messageDataSource.sendDirectMessage(type, to, content, queueId, localId) }
        return sendDirectMessageDto.toSendMessage()
    }

    override suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String,
        propagateMode: String,
        sendNotificationToOldThread: Boolean,
        sendNotificationToNewThread: Boolean
    ) {
        wrapApiCall {
            messageDataSource.editMessage(
                messageId,
                content,
                topic,
                propagateMode,
                sendNotificationToOldThread,
                sendNotificationToNewThread
            )
        }
    }

    override suspend fun deleteMessage(messageId: Int) {
        wrapApiCall { messageDataSource.deleteMessage(messageId) }
    }

    override suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>?,
        clientGravatar: Boolean,
        applyMarkdown: Boolean
    ): Messages {
        val messagesDto = wrapApiCall {
            messageDataSource.getMessages(
                anchor,
                includeAnchor,
                numBefore,
                numAfter,
                narrow,
                clientGravatar,
                applyMarkdown
            )
        }
        return messagesDto.toMessages()
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ) {
        wrapApiCall {
            messageDataSource.addEmojiReaction(
                messageId,
                emojiName,
                emojiCode,
                reactionType
            )
        }
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ) {
        wrapApiCall {
            messageDataSource.deleteEmojiReaction(
                messageId,
                emojiName,
                emojiCode,
                reactionType
            )
        }
    }

    override suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String
    ): PersonalMessage {
        val personalMessageDto =
            wrapApiCall { messageDataSource.updateMessageFlags(messages, op, flag) }
        return personalMessageDto.toPersonalMessage()
    }

    override suspend fun markAllMessagesAsRead() {
        wrapApiCall { messageDataSource.markAllMessagesAsRead() }
    }

    override suspend fun markStreamAsRead(steamId: Int) {
        wrapApiCall { messageDataSource.markStreamAsRead(steamId) }
    }

    override suspend fun markTopicAsRead(steamId: Int, topicName: String) {
        wrapApiCall { messageDataSource.markTopicAsRead(steamId, topicName) }
    }

    override suspend fun getMessageReadReceipts(messageId: Int): MessageReadReceipts {
        val messageReadReceiptsDto =
            wrapApiCall { messageDataSource.getMessageReadReceipts(messageId) }
        return messageReadReceiptsDto.toMessageReadReceipts()
    }

    override suspend fun uploadFile(file: File): AttachmentMessage {
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)
        return wrapApiCall { messageDataSource.uploadFile(filePart) }.toAttachmentMessage()
    }


    override suspend fun renderMessage(content: String): RenderMessage {
        return wrapApiCall { messageDataSource.renderMessage(content) }.toRenderMessage()
    }

    override suspend fun fetchSingleMethod(messageId: Int): SingleMessage {
        return wrapApiCall { messageDataSource.fetchSingleMessage(messageId) }.toSingleMessage()
    }

    override suspend fun checkIfMessagesMatchNarrow(
        messagesIds: String,
        narrow: String
    ): MatchNarrow {
        return wrapApiCall {
            messageDataSource.checkIfMessagesMatchNarrow(
                messagesIds,
                narrow
            )
        }.toMatchNarrow()
    }

    override suspend fun getMessagesEditHistory(messageId: Int): List<MessageEditHistory> {
        return wrapApiCall { messageDataSource.getMessagesEditHistory(messageId) }.toMessageEditHistory()
    }

    override suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        includeAnchor: Boolean,
        narrow: String,
        op: String,
        flag: String
    ): PersonalMessageForNarrow {
        return wrapApiCall {
            messageDataSource.updatePersonalMessageFlagsForNarrow(
                anchor, numBefore, numAfter, includeAnchor, narrow, op, flag
            )
        }.toPersonalMessageForNarrow()
    }

}