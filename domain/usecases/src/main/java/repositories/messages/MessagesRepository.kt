package repositories.messages

import com.chocolate.entities.messages.NarrowItemEntity

interface MessagesRepository {

    suspend fun sendStreamMessage(
        type: String,
        to: String,
        topic: String?,
        content: String
    )

    suspend fun sendDirectMessage(
        type: String,
        to: String,
        content: String
    )

    suspend fun uploadFile(filePath: String)

    suspend fun editMessage(
        message_id: Int,
        content: String,
        topic: String?,
        propagateMode: String?,
        sendNotificationToOldThread: Boolean = false,
        sendNotificationToNewThread: Boolean = true
    )

    suspend fun deleteMessage(
        message_id: Int
    )

    suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean = true,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>?,
        clientGravatar: Boolean = true,
        applyMarkdown: Boolean = true
    )

    suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?

    )

    suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    )

    suspend fun renderMessage(
        content: String,
    )

    suspend fun fetchSingleMessage(
        messageId: Int
    )

    suspend fun checkIfMessagesMatchNarrow(
        msgIds: List<Int>,
        narrow: List<NarrowItemEntity>
    )

    suspend fun getMessagesEditHistory(
        messageId: Int
    )

    suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String,

    )

    suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        narrow: List<Map<String, String>>,
        op: String,
        flag: String
    )

    suspend fun markAllMessagesAsRead()

    suspend fun markStreamAsRead(
        steamId: Int,
    )

    suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    )

    suspend fun getMessageReadReceipts(
        messageId: Int
    )
}