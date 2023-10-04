package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.entity.Chat
import com.chocolate.entities.util.EmptyOrganizationNameException
import com.chocolate.entities.entity.Message
import com.chocolate.repository.datasource.local.PreferencesDataSource
import com.chocolate.repository.datasource.remote.DirectMessageDataSource
import com.chocolate.repository.datasource.remote.MemberDataSource
import com.chocolate.repository.mappers.toMessage
import com.chocolate.repository.mappers.toMessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.chocolate.usecases.repositories.DirectMessageRepository
import java.util.Date
import javax.inject.Inject

class DirectMessageRepositoryImpl @Inject constructor(
    private val directMessageDataSource: DirectMessageDataSource,
    private val memberDataSource: MemberDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : DirectMessageRepository {
    override suspend fun getChatsByUserId(
        memberId: String,
    ): Flow<List<Chat>> {
        val chats =
            directMessageDataSource.getChatsByMemberId(memberId, getCurrentOrganizationName())
        return chats.map {
            it.map {
                val member =
                    memberDataSource.getMemberInOrganizationById(
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
        return directMessageDataSource.fetchMessagesByGroupId(
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
        directMessageDataSource.sendMessage(
            message.toMessageDto(),
            getCurrentOrganizationName(),
            currentChatId
        )
    }

    override suspend fun createGroup(
        memberIds: List<String>,
    ): String {
        return directMessageDataSource.createGroup(memberIds, getCurrentOrganizationName())
    }

    private suspend fun getCurrentOrganizationName(): String =
        preferencesDataSource.getCurrentOrganizationName()
            ?: throw EmptyOrganizationNameException
}