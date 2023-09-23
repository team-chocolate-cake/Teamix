package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.messages.Message
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.chocolate.repository.datastore.remote.MemberRemoteDataSource
import com.chocolate.repository.mappers.messages.toMessage
import com.chocolate.repository.mappers.messages.toMessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.DirectMessageRepository
import java.util.Date
import javax.inject.Inject

class DirectMessageRepositoryImpl @Inject constructor(
    private val directMessageRemoteDataSource: DirectMessageRemoteDataSource,
    private val memberRemoteDataSource: MemberRemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : DirectMessageRepository {

    override suspend fun getChatsByUserId(
        memberId: String,
    ): Flow<List<Chat>> {
        val chats =
            directMessageRemoteDataSource.getChatsByUserId(memberId, getCurrentOrganizationName())
        return chats.map {
            it.map {
                val member =
                    memberRemoteDataSource.getMemberInOrganizationById(
                        it.secondMemberId,
                        getCurrentOrganizationName()
                    )
                Chat(
                    id = it.id,
                    lastMessage = it.lastMessage,
                    lastMessageDate = Date(),
                    name = member?.name ?: "",
                    image = member?.imageUrl ?: ""
                )
            }
        }
    }

    override suspend fun fetchMessagesByGroupId(
        chatId: String,
    ): Flow<List<Message>> {
        return directMessageRemoteDataSource.fetchMessagesByGroupId(
            chatId,
            getCurrentOrganizationName()
        )
            .map { it.toMessage() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sendMessage(
        message: Message,
        currentChatId: String
    ) {
        directMessageRemoteDataSource.sendMessage(
            message.toMessageDto(),
            getCurrentOrganizationName(),
            currentChatId
        )
    }

    override suspend fun createGroup(
        memberIds: List<String>,
    ): String {
        return directMessageRemoteDataSource.createGroup(memberIds, getCurrentOrganizationName())
    }

    private suspend fun getCurrentOrganizationName(): String =
        preferencesDataSource.getCurrentOrganizationName()
            ?: throw EmptyOrganizationNameException

}