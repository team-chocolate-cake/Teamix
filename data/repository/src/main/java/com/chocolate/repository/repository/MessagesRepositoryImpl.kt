package com.chocolate.repository.repository

import com.chocolate.entities.draft.Draft
import com.chocolate.entities.messages.Message
import com.chocolate.repository.datastore.local.LocalDataSource
import com.chocolate.repository.datastore.realtime.TopicDataSource
import com.chocolate.repository.datastore.remote.MessagesRemoteDataSource
import com.chocolate.repository.mappers.draft.toEntity
import com.chocolate.repository.mappers.messages.toEntity
import com.chocolate.repository.mappers.messages.toLocalDto
import com.chocolate.repository.mappers.messages.toMessage
import com.chocolate.repository.mappers.messages.toMessageDto
import com.chocolate.repository.utils.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import repositories.MessagesRepository
import java.io.File
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val messageDataSource: MessagesRemoteDataSource,
    private val topicDataSource: TopicDataSource,
    private val teamixLocalDataSource: LocalDataSource,
) : MessagesRepository {

    override suspend fun getDrafts(): List<Draft> {
        return messageDataSource.getDrafts().drafts.toEntity()
    }

    override suspend fun createDraft(
        type: String,
        recipients: Int,
        topic: String,
        content: String
    ): List<Int> {
        return messageDataSource.createDraft(
            type = type,
            content = content,
            topic = topic,
            recipients = recipients,
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

    override suspend fun sendStreamMessage(message: Message) {
        topicDataSource.sendMessage(message.toMessageDto(), 1234, "test")
    }

    override suspend fun getMessages(topicId: Int,channelId:Int,organizationName: String): Flow<List<Message>?> {
        return topicDataSource.getMessages(topicId,channelId,organizationName).map { messages ->
            messages.map {
                it.toMessage()
            }
        }
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


    override suspend fun checkIfMessagesMatchNarrow(
        messagesIds: String,
        narrow: String
    ): String {
        return messageDataSource.checkIfMessagesMatchNarrow(
            messagesIds,
            narrow
        ).messages?.messageId?.matchContent.orEmpty()
    }

    override suspend fun getSavedMessages(): List<Message> {
        return teamixLocalDataSource.getSavedMessages().map { it.toEntity() }
    }

    override suspend fun saveMessage(message: Message) {
        teamixLocalDataSource.saveMessage(message.toLocalDto())
    }

    override suspend fun deleteSavedMessageById(id: Int) {
        teamixLocalDataSource.deleteSavedMessageById(id)
    }
}