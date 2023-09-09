package repositories

import com.chocolate.entities.messages.Message
import com.chocolate.entities.messages.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    suspend fun createTopic(channelName: String, topicContent: String, senderId: String)
    suspend fun getTopicsInAChannel(channelId: String): Flow<List<Topic>>

    suspend fun sendMessage(message:Message,channelId:Int,organizationName: String)
    suspend fun getMessages(channelId: Int): Flow<List<Message>>

}