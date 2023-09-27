package repositories

import com.chocolate.entities.Draft
import com.chocolate.entities.Message
import com.chocolate.entities.SavedLaterMessage
import kotlinx.coroutines.flow.Flow

interface TopicsMessageRepository {
    suspend fun getDrafts(): List<Draft>

    suspend fun createDraft(
        type: String,
        recipients: Int,
        topic: String,
        content: String
    ): List<Int>


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

    suspend fun getSavedLaterMessages(): Flow<List<SavedLaterMessage>>

    suspend fun saveMessage(message: SavedLaterMessage)

    suspend fun deleteSavedLaterMessageById(savedLaterMessageId: String)
}