package com.chocolate.repository.repository

import com.chocolate.entities.draft.Draft
import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.messages.Message
import com.chocolate.repository.datastore.remote.MessagesRemoteDataSource
import com.chocolate.repository.mappers.draft.toEntity
import com.chocolate.repository.mappers.messages.toEntity
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
        type: String,
        recipients: List<Int>,
        topic: String,
        content: String
    ) {
        messageDataSource.editDraft(
            type = type,
            content = content,
            topic = topic,
            recipients = recipients.toJson(),
        )
    }

    override suspend fun deleteDraft(id: Int) {
        messageDataSource.deleteDraft(id)
    }

    override suspend fun sendStreamMessage(
        type: String,
        recipients: String,
        topic: String,
        content: String
    ): Int {
        val sendSteamMessageDto = messageDataSource.sendStreamMessage(
            type,
            recipients,
            topic,
            content
        )
        return sendSteamMessageDto.id ?: -1
    }

    override suspend fun sendDirectMessage(
        type: String,
        recipients: String,
        content: String
    ): Int {
        val sendDirectMessageDto =
            messageDataSource.sendDirectMessage(type, recipients, content)
        return sendDirectMessageDto.id ?: -1
    }

    override suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String
    ) {
        messageDataSource.editMessage(
            messageId,
            content,
            topic
        )
    }

    override suspend fun deleteMessage(messageId: Int) {
        messageDataSource.deleteMessage(messageId)
    }

    override suspend fun getMessages(
        anchor: String?,
        numBefore: Int,
        numAfter: Int,
    ): List<Message> {
        val messagesDto = messageDataSource.getMessages(
            anchor,
            numBefore,
            numAfter
        )
        return messagesDto.messages.toEntity()
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String
    ) {
        messageDataSource.addEmojiReaction(
            messageId,
            emojiName
        )
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String
    ) {
        messageDataSource.deleteEmojiReaction(
            messageId,
            emojiName,
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
}