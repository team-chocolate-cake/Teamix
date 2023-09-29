package repositories

import com.chocolate.entities.entity.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    suspend fun createTopic(channelId: String, topic: Topic): String

    suspend fun getTopicsInChannel(channelId: String): Flow<List<Topic>>

    suspend fun getSavedTopics(): Flow<List<Topic>>

    suspend fun saveTopic(topic: Topic)

    suspend fun deleteSavedTopicById(topicId: String)
}