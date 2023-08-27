package repositories

import com.chocolate.entities.draft.Draft
import com.chocolate.entities.messages.Message
import java.io.File

interface MessagesRepository {

    suspend fun getDrafts(): List<Draft>

    suspend fun createDraft(
        type: String,
        recipients: Int,
        topic: String,
        content: String
    ): List<Int>

    suspend fun editDraft(
        type: String,
        recipients: List<Int>,
        topic: String,
        content: String,
    )

    suspend fun deleteDraft(id: Int)
    suspend fun sendStreamMessage(
        type: String,
        recipients: String,
        topic: String,
        content: String
    ): Int

    suspend fun sendDirectMessage(
        type: String,
        recipients: String,
        content: String
    ): Int

    suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String = "",
    )

    suspend fun deleteMessage(messageId: Int)

    suspend fun getMessages(
        anchor: String?,
        numBefore: Int,
        numAfter: Int,
    ): List<Message>?

    suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String
    )

    suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String
    )

    suspend fun markAllMessagesAsRead()

    suspend fun markStreamAsRead(steamId: Int)

    suspend fun markTopicAsRead(steamId: Int, topicName: String)

    suspend fun uploadFile(file: File): String?

    suspend fun fetchSingleMethod(messageId: Int): Message

    suspend fun checkIfMessagesMatchNarrow(messagesIds: String, narrow: String): String

}