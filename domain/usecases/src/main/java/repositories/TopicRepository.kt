package repositories

import com.chocolate.entities.topic.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    suspend fun createTopic(channelId:String, topic: Topic, organizationName:String)
    suspend fun getTopicsInChannel(channelId: String, organizationName:String): Flow<List<Topic>>

}