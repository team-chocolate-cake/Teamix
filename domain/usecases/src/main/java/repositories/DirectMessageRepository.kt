package repositories

import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DMMessage

interface DirectMessageRepository {
    suspend fun getChatsByUserId(userid: String): List<Chat>

    suspend fun fetchMessagesByGroupId(groupId: String) :List<DMMessage>

    suspend fun sendMessage(message: DMMessage, currentGroupId: String)
}