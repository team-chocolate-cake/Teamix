package repositories

import com.chocolate.entities.directMessage.DMChat
import com.chocolate.entities.directMessage.DMMessage

interface DirectMessageRepository {
    suspend fun getChatsByUserId(userid: String, currentOrgName: String): List<DMChat>

    suspend fun fetchMessagesByGroupId(groupId: String, currentOrgName: String) :List<DMMessage>

    suspend fun sendMessage(message: DMMessage, currentGroupId: String)
    suspend fun createGroup(userids: List<String>, currentOrgName: String): String

}