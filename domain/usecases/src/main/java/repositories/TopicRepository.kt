package repositories

import com.chocolate.entities.topic.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    suspend fun createTopic(channelId: String, topic: Topic): String
    suspend fun getTopicsInChannel(channelId: String): Flow<List<Topic>>

}