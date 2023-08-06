package repositories.messages

import com.chocolate.entities.messages.AttachmentMessage
import com.chocolate.entities.messages.MatchNarrow
import com.chocolate.entities.messages.MessageEditHistory
import com.chocolate.entities.messages.MessageReadReceipts
import com.chocolate.entities.messages.Messages
import com.chocolate.entities.messages.PersonalMessage
import com.chocolate.entities.messages.PersonalMessageForNarrow
import com.chocolate.entities.messages.RenderMessage
import com.chocolate.entities.messages.SendMessage
import com.chocolate.entities.messages.SingleMessage
import java.io.File

interface MessagesRepository {

    suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String? = null,
        localId: String? = null,
    ): SendMessage

    suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String? = null,
        localId: String? = null,
    ): SendMessage

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
    ): Messages

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

    suspend fun updateMessageFlags(messages: List<Int>, op: String, flag: String): PersonalMessage

    suspend fun markAllMessagesAsRead()

    suspend fun markStreamAsRead(steamId: Int)

    suspend fun markTopicAsRead(steamId: Int, topicName: String)

    suspend fun getMessageReadReceipts(messageId: Int): MessageReadReceipts

    suspend fun uploadFile(file: File): AttachmentMessage

    suspend fun renderMessage(content: String): RenderMessage

    suspend fun fetchSingleMethod(messageId: Int): SingleMessage

    suspend fun checkIfMessagesMatchNarrow(messagesIds: String, narrow: String): MatchNarrow

    suspend fun getMessagesEditHistory(messageId: Int): List<MessageEditHistory>

    suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        includeAnchor: Boolean = true,
        narrow: String,
        op: String,
        flag: String
    ): PersonalMessageForNarrow
}