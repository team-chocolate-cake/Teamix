package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.Message
import com.chocolate.entities.entity.SavedLaterMessage
import com.chocolate.entities.util.EmptyMemberIdException
import com.chocolate.entities.util.EmptyOrganizationNameException
import com.chocolate.repository.datasource.local.PreferencesDataSource
import com.chocolate.repository.datasource.remote.MemberDataSource
import com.chocolate.repository.datasource.remote.SavedLaterDataSource
import com.chocolate.repository.datasource.remote.TopicMessagesDataSource
import com.chocolate.repository.mappers.toEntity
import com.chocolate.repository.mappers.toMessage
import com.chocolate.repository.mappers.toMessageDto
import com.chocolate.repository.mappers.toRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.TopicsMessageRepository
import javax.inject.Inject

class TopicsMessageRepositoryImpl @Inject constructor(
    private val topicMessagesDataSource: TopicMessagesDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val savedLaterDataSource: SavedLaterDataSource,
    private val memberDataSource: MemberDataSource
) : TopicsMessageRepository {
    private suspend fun getCurrentOrganizationName(): String =
        preferencesDataSource.getCurrentOrganizationName()
            ?: throw EmptyOrganizationNameException

    private suspend fun getCurrentMember(): Member {
        val currentMemberId =
            preferencesDataSource.getIdOfCurrentMember() ?: throw EmptyMemberIdException
        return memberDataSource.getMemberInOrganizationById(
            currentMemberId,
            getCurrentOrganizationName()
        )?.toEntity() ?: throw EmptyMemberIdException
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sendMessageInTopic(
        message: Message,
        topicId: String,
        channelId: String,
        organizationName: String
    ) {
        topicMessagesDataSource.sendMessageInTopic(
            message = message.toMessageDto(),
            topicId = topicId,
            channelId = channelId,
            "teamixOrganization"
        )
    }

    override suspend fun getMessagesFromTopic(
        topicId: String,
        channelId: String,
        organizationName: String
    ): Flow<List<Message>> {
        return topicMessagesDataSource.getMessagesFromTopic(
            topicId,
            channelId,
            "teamixOrganization"
        ).map { it.toMessage() }
    }

    override suspend fun getSavedLaterMessages(): Flow<List<SavedLaterMessage>> {
        return savedLaterDataSource.getSavedLaterMessages(
            getCurrentOrganizationName(),
            getCurrentMember().id
        ).toEntity(getCurrentMember())
    }

    override suspend fun saveMessage(message: SavedLaterMessage) {
        val member = memberDataSource.getMemberInOrganizationById(message.sender.id, getCurrentOrganizationName())
        val newMessage = message.copy(
            sender = member?.toEntity() ?: throw EmptyMemberIdException
        )
        savedLaterDataSource.addSavedLaterMessage(getCurrentOrganizationName(), newMessage.toRemote())
    }

    override suspend fun deleteSavedLaterMessageById(savedLaterMessageId: String) {
        savedLaterDataSource.deleteSavedLaterMessageById(
            getCurrentOrganizationName(),
            getCurrentMember().id,
            savedLaterMessageId
        )
    }
}