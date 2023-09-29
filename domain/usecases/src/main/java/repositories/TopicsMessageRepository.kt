package repositories

import com.chocolate.entities.entity.Message
import com.chocolate.entities.entity.SavedLaterMessage
import kotlinx.coroutines.flow.Flow

interface TopicsMessageRepository {

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