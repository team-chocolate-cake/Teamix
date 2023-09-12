package repositories

import com.chocolate.entities.draft.Draft
import com.chocolate.entities.messages.Message
import kotlinx.coroutines.flow.Flow
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
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    )

    suspend fun deleteDraft(id: Int)

    suspend fun sendMessageInTopic(
        message: Message,
        topicId: String,
        channelId: String,
        organizationName: String,
    )

    suspend fun getMessagesFromTopic(
        topicId: String,
        channelId: String,
        organizationName: String
    ): Flow<List<Message>>


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


    suspend fun checkIfMessagesMatchNarrow(messagesIds: String, narrow: String): String

    //   suspend fun getSavedMessages(): List<Message>

    //   suspend fun saveMessage(message: Message)

    suspend fun deleteSavedMessageById(id: Int)


}