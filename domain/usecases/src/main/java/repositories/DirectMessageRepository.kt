package repositories

import com.chocolate.entities.directMessage.ChatEntity
import com.chocolate.entities.directMessage.MessageEntity
import kotlinx.coroutines.flow.Flow

interface DirectMessageRepository {
    suspend fun getChatsByUserId(memberId: String, currentOrganizationName: String): Flow<List<ChatEntity>>

    suspend fun fetchMessagesByGroupId(chatId: String, currentOrganizationName: String) : Flow<List<MessageEntity>>

    suspend fun sendMessage(message: MessageEntity, currentOrganizationName: String, currentChatId: String)
    suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String

}