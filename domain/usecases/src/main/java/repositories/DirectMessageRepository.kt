package repositories

import com.chocolate.entities.directMessage.DMChat
import com.chocolate.entities.directMessage.DMMessage
import kotlinx.coroutines.flow.Flow

interface DirectMessageRepository {
    suspend fun getChatsByUserId(memberId: String, currentOrganizationName: String): Flow<List<DMChat>>

    suspend fun fetchMessagesByGroupId(chatId: String, currentOrganizationName: String) : Flow<List<DMMessage>>

    suspend fun sendMessage(message: DMMessage, currentOrganizationName: String, currentChatId: String)
    suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String

}