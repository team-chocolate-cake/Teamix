package repositories

import com.chocolate.entities.directMessage.DMChat
import com.chocolate.entities.directMessage.DMMessage
import kotlinx.coroutines.flow.Flow

interface DirectMessageRepository {
    suspend fun getChatsByUserId(userid: String, currentOrgName: String): Flow<List<DMChat>>

    suspend fun fetchMessagesByGroupId(groupId: String, currentOrgName: String) : Flow<List<DMMessage>>

    suspend fun sendMessage(message: DMMessage, currentOrgName: String, currentGroupId: String)
    suspend fun createGroup(userids: List<String>, currentOrgName: String): String

}