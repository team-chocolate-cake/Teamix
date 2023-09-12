package com.chocolate.repository.repository

import com.chocolate.entities.draft.Draft
import com.chocolate.entities.messages.Message
import com.chocolate.repository.datastore.local.LocalDataSource
import com.chocolate.repository.datastore.realtime.TopicDataSource
import com.chocolate.repository.datastore.remote.MessagesRemoteDataSource
import com.chocolate.repository.mappers.draft.toEntity
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
    private val messagesRemoteDataSource: MessagesRemoteDataSource,
    private val teamixLocalDataSource: LocalDataSource,
) : MessagesRepository {

    override suspend fun getDrafts(): List<Draft> {
        return messagesRemoteDataSource.getDrafts().drafts.toEntity()
    }

    override suspend fun createDraft(
        type: String,
        recipients: Int,
        topic: String,
        content: String
    ): List<Int> {
        return messagesRemoteDataSource.createDraft(
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
        messagesRemoteDataSource.editDraft(
            id = id,
            type = type,
            content = content,
            topic = topic,
            to = to.toJson(),
            timestamp = timestamp
        )
    }

    override suspend fun deleteDraft(id: Int) {
        messagesRemoteDataSource.deleteDraft(id)
    }

    override suspend fun sendMessageInTopic(
        message: Message,
        topicId: String,
        channelId: String,
        organizationName: String
    ) {
        messagesRemoteDataSource.sendMessageInTopic(
            message=message.toMessageDto(),
            topicId= topicId,
            channelId=channelId,
            "teamixOrganization"
        )
    }

    override suspend fun getMessagesFromTopic(
        topicId: String,
        channelId: String,
        organizationName: String
    ): Flow<List<Message>> {
        return messagesRemoteDataSource.getMessagesFromTopic(
            topicId,
            channelId,
            "teamixOrganization"
        ).map { it.toMessage() }
    }


//    override suspend fun sendStreamMessage(message: Message) {
//
//    }
//
//    override suspend fun getMessages(topicId: Int,channelId:Int,organizationName: String): Flow<List<Message>?> {
//
//    }

    override suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?
    ): Int {
        val sendDirectMessageDto =
            messagesRemoteDataSource.sendDirectMessage(type, to, content, queueId, localId)
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
        messagesRemoteDataSource.editMessage(
            messageId,
            content,
            topic,
            propagateMode,
            sendNotificationToOldThread,
            sendNotificationToNewThread
        )
    }

    override suspend fun deleteMessage(messageId: Int) {
        messagesRemoteDataSource.deleteMessage(messageId)
    }


    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ) {
        messagesRemoteDataSource.addEmojiReaction(
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
        messagesRemoteDataSource.deleteEmojiReaction(
            messageId,
            emojiName,
            emojiCode,
            reactionType
        )
    }

    override suspend fun markAllMessagesAsRead() {
        messagesRemoteDataSource.markAllMessagesAsRead()
    }

    override suspend fun markStreamAsRead(steamId: Int) {
        messagesRemoteDataSource.markStreamAsRead(steamId)
    }

    override suspend fun markTopicAsRead(steamId: Int, topicName: String) {
        messagesRemoteDataSource.markTopicAsRead(steamId, topicName)
    }

    override suspend fun uploadFile(file: File): String {
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)
        return messagesRemoteDataSource.uploadFile(filePart).uri.orEmpty()
    }


    override suspend fun checkIfMessagesMatchNarrow(
        messagesIds: String,
        narrow: String
    ): String {
        return messagesRemoteDataSource.checkIfMessagesMatchNarrow(
            messagesIds,
            narrow
        ).messages?.messageId?.matchContent.orEmpty()
    }

//    override suspend fun getSavedMessages(): List<Message> {
//        return teamixLocalDataSource.getSavedMessages().map { it.toEntity() }
//    }
//
//    override suspend fun saveMessage(message: Message) {
//        teamixLocalDataSource.saveMessage(message.toLocalDto())
//    }

    override suspend fun deleteSavedMessageById(id: Int) {
        teamixLocalDataSource.deleteSavedMessageById(id)
    }
}