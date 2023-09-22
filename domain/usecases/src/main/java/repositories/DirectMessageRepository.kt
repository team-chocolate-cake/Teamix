package repositories

import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DirectMessage
import kotlinx.coroutines.flow.Flow

interface DirectMessageRepository {
    suspend fun getChatsByUserId(memberId: String, currentOrganizationName: String): Flow<List<Chat>>

    suspend fun fetchMessagesByGroupId(chatId: String, currentOrganizationName: String) : Flow<List<DirectMessage>>

    suspend fun sendMessage(directMessage: DirectMessage, currentOrganizationName: String, currentChatId: String)
    suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String

}