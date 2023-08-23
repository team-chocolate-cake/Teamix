package com.chocolate.repository.repository

import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.messages.Message
import com.chocolate.repository.mappers.messages.toEntity
import com.chocolate.repository.service.remote.RemoteDataSource
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import repositories.MessagesRepository
import java.io.File
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val messageDataSource: RemoteDataSource
) : MessagesRepository {
    override suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String?,
        localId: String?
    ): Int {
        val sendSteamMessageDto = messageDataSource.sendStreamMessage(
            type,
            to,
            topic,
            content,
            queueId,
            localId
        )
        return sendSteamMessageDto.id ?: -1
    }

    override suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?
    ): Int {
        val sendDirectMessageDto =
            messageDataSource.sendDirectMessage(type, to, content, queueId, localId)
        return sendDirectMessageDto.id ?: -1
    }

    override suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String,
        propagateMode: String,
        sendNotificationToOldThread: Boolean,
        sendNotificationToNewThread: Boolean
    ) {
        messageDataSource.editMessage(
            messageId,
            content,
            topic,
            propagateMode,
            sendNotificationToOldThread,
            sendNotificationToNewThread
        )
    }

    override suspend fun deleteMessage(messageId: Int) {
        messageDataSource.deleteMessage(messageId)
    }

    override suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>?,
        clientGravatar: Boolean,
        applyMarkdown: Boolean
    ): List<Message>? {
        val messagesDto = messageDataSource.getMessages(
            anchor,
            includeAnchor,
            numBefore,
            numAfter,
            narrow,
            clientGravatar,
            applyMarkdown
        )
        return messagesDto.messages.toEntity()
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ) {
        messageDataSource.addEmojiReaction(
            messageId,
            emojiName,
            emojiCode,
            reactionType
        )
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ) {
        messageDataSource.deleteEmojiReaction(
            messageId,
            emojiName,
            emojiCode,
            reactionType
        )
    }

//    override suspend fun updateMessageFlags(
//        messages: List<Int>,
//        op: String,
//        flag: String
//    ): PersonalMessage {
//        val personalMessageDto = messageDataSource.updateMessageFlags(messages, op, flag)
//        return personalMessageDto.toPersonalMessage()
//    }

    override suspend fun markAllMessagesAsRead() {
        messageDataSource.markAllMessagesAsRead()
    }

    override suspend fun markStreamAsRead(steamId: Int) {
        messageDataSource.markStreamAsRead(steamId)
    }

    override suspend fun markTopicAsRead(steamId: Int, topicName: String) {
        messageDataSource.markTopicAsRead(steamId, topicName)
    }

//    override suspend fun getMessageReadReceipts(messageId: Int): MessageReadReceipts {
//        val messageReadReceiptsDto = messageDataSource.getMessageReadReceipts(messageId)
//        return messageReadReceiptsDto.toMessageReadReceipts()
//    }

    override suspend fun uploadFile(file: File): String {
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)
        return messageDataSource.uploadFile(filePart).uri.orEmpty()
    }

//    override suspend fun renderMessage(content: String): RenderMessage {
//        return messageDataSource.renderMessage(content).toRenderMessage()
//    }

    override suspend fun fetchSingleMethod(messageId: Int): Message {
        return messageDataSource.fetchSingleMessage(messageId).message?.toEntity() ?: throw NullDataException("")
    }

    override suspend fun checkIfMessagesMatchNarrow(
        messagesIds: String,
        narrow: String
    ): String {
        return messageDataSource.checkIfMessagesMatchNarrow(
            messagesIds,
            narrow
        ).messages?.messageId?.matchContent.orEmpty()
    }

    //todo we will not use this
//    override suspend fun getMessagesEditHistory(messageId: Int): List<MessageEditHistory> {
//        return messageDataSource.getMessagesEditHistory(messageId).toMessageEditHistory()
//    }

//    override suspend fun updatePersonalMessageFlagsForNarrow(
//        anchor: String,
//        numBefore: Int,
//        numAfter: Int,
//        includeAnchor: Boolean,
//        narrow: String,
//        op: String,
//        flag: String
//    ): PersonalMessageForNarrow {
//        return messageDataSource.updatePersonalMessageFlagsForNarrow(
//            anchor, numBefore, numAfter, includeAnchor, narrow, op, flag
//        ).toPersonalMessageForNarrow()
//    }
}