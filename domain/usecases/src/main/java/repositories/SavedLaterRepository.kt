package repositories

import com.chocolate.entities.entity.SavedMessage
import com.chocolate.entities.entity.Topic
import kotlinx.coroutines.flow.Flow

interface SavedLaterRepository {

    suspend fun getSavedTopics(): Flow<List<Topic>>

    suspend fun saveTopic(topic: Topic)

    suspend fun deleteSavedTopicById(topicId: String)

    suspend fun getSavedLaterMessages(): Flow<List<SavedMessage>>

    suspend fun saveMessage(message: SavedMessage)

    suspend fun deleteSavedLaterMessageById(savedLaterMessageId: String)
}