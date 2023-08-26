package com.chocolate.repository.repository

import com.chocolate.entities.draft.Draft
import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.messages.Message
import com.chocolate.entities.scheduled_messages.ScheduledMessage
import com.chocolate.repository.datastore.remote.MessagesRemoteDataSource
import com.chocolate.repository.mappers.draft.toEntity
import com.chocolate.repository.mappers.messages.toEntity
import com.chocolate.repository.mappers.scheduled.toEntity
import com.chocolate.repository.utils.toJson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import repositories.MessagesRepository
import java.io.File
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val messageDataSource: MessagesRemoteDataSource
) : MessagesRepository {

    override suspend fun getDrafts(): List<Draft> {
        return messageDataSource.getDrafts().drafts.toEntity()
    }

    override suspend fun createDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    ): List<Int> {
        return messageDataSource.createDraft(
            id = id,
            type = type,
            content = content,
            topic = topic,
            to = to.toJson(),
            timestamp = timestamp
        ).ids ?: emptyList()
    }

    override suspend fun editDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    ) {
        messageDataSource.editDraft(
            id = id,
            type = type,
            content = content,
            topic = topic,
            to = to.toJson(),
            timestamp = timestamp
        )
    }

    override suspend fun deleteDraft(id: Int) {
        messageDataSource.deleteDraft(id)
    }

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
    ): List<Message> {
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

    override suspend fun markAllMessagesAsRead() {
        messageDataSource.markAllMessagesAsRead()
    }

    override suspend fun markStreamAsRead(steamId: Int) {
        messageDataSource.markStreamAsRead(steamId)
    }

    override suspend fun markTopicAsRead(steamId: Int, topicName: String) {
        messageDataSource.markTopicAsRead(steamId, topicName)
    }

    override suspend fun uploadFile(file: File): String {
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)
        return messageDataSource.uploadFile(filePart).uri.orEmpty()
    }

    override suspend fun fetchSingleMethod(messageId: Int): Message {
        return messageDataSource.fetchSingleMessage(messageId).message?.toEntity()
            ?: throw NullDataException("")
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

    override suspend fun getScheduledMessages(): List<ScheduledMessage> {
        return messageDataSource.getScheduledMessages().scheduledMessages.toEntity()
    }

    override suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Int {
        return messageDataSource.createScheduledMessage(
            type, to, content, topic, scheduledDeliveryTimestamp
        ).scheduledMessageId ?: -1
    }

    override suspend fun editScheduledMessage(
        id: Int,
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Int {
        return messageDataSource.editScheduledMessage(
            id, type, to, content, topic, scheduledDeliveryTimestamp
        ).scheduledMessageId ?: -1
    }

    override suspend fun deleteScheduledMessage(id: Int) {
        messageDataSource.deleteScheduledMessage(id)
    }
}