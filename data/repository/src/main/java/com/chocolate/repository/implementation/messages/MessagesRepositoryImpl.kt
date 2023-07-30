package com.chocolate.repository.implementation.messages

import com.chocolate.repository.service.MessagesDataSource
import repositories.messages.MessagesRepository
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val messageService: MessagesDataSource
): MessagesRepository{
    override suspend fun sendStreamMessage(
        type: String,
        to: String,
        topic: String?,
        content: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun sendDirectMessage(type: String, to: String, content: String) {
        TODO("Not yet implemented")
    }

    override suspend fun uploadFile(filePath: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editMessage(
        message_id: Int,
        content: String,
        topic: String?,
        propagateMode: String?,
        sendNotificationToOldThread: Boolean,
        sendNotificationToNewThread: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMessage(message_id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean,
        numberBefore: Int,
        numberAfter: Int,
        narrow: List<String>?,
        clientGravatar: Boolean,
        applyMarkdown: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun renderMessage(content: String) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSingleMessage(messageId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun checkIfMessagesMatchNarrow(
        messageIds: List<Int>,
        narrow: List<Map<String, String>>
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessagesEditHistory(messageId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMessageFlags(messages: List<Int>, op: String, flag: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        narrow: List<Map<String, String>>,
        op: String,
        flag: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun markAllMessagesAsRead() {
        TODO("Not yet implemented")
    }

    override suspend fun markStreamAsRead(steamId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun markTopicAsRead(steamId: Int, topicName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessageReadReceipts(messageId: Int) {
        TODO("Not yet implemented")
    }

}