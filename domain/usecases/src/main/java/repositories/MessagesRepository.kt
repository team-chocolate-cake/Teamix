package repositories

import com.chocolate.entities.draft.Draft
import com.chocolate.entities.messages.Message
import com.chocolate.entities.scheduled_messages.ScheduledMessage
import java.io.File

interface MessagesRepository {

    suspend fun getDrafts(): List<Draft>

    suspend fun createDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    ): List<Int>

    suspend fun editDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    )

    suspend fun deleteDraft(id: Int)
    suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String? = null,
        localId: String? = null,
    ): Int

    suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String? = null,
        localId: String? = null,
    ): Int

    suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String = "",
        propagateMode: String = "change_one",
        sendNotificationToOldThread: Boolean = false,
        sendNotificationToNewThread: Boolean = true
    )

    suspend fun deleteMessage(messageId: Int)

    suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean = true,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>? = null,
        clientGravatar: Boolean = true,
        applyMarkdown: Boolean = true
    ): List<Message>?

    suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String? = null,
        reactionType: String? = null
    )

    suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String? = null,
        reactionType: String? = null
    )

    suspend fun markAllMessagesAsRead()

    suspend fun markStreamAsRead(steamId: Int)

    suspend fun markTopicAsRead(steamId: Int, topicName: String)

    suspend fun uploadFile(file: File): String?

    suspend fun fetchSingleMethod(messageId: Int): Message

    suspend fun checkIfMessagesMatchNarrow(messagesIds: String, narrow: String): String

    suspend fun getScheduledMessages(): List<ScheduledMessage>

    suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Int

    suspend fun editScheduledMessage(
        id: Int,
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Int

    suspend fun deleteScheduledMessage(id: Int)
}