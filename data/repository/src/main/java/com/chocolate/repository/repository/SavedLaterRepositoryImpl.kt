package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.Message
import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.EmptyMemberIdException
import com.chocolate.entities.util.EmptyOrganizationNameException
import com.chocolate.repository.datasource.local.PreferencesDataSource
import com.chocolate.repository.datasource.remote.MemberDataSource
import com.chocolate.repository.datasource.remote.SavedLaterDataSource
import com.chocolate.repository.mappers.toEntity
import com.chocolate.repository.mappers.toMessage
import com.chocolate.repository.mappers.toMessageDto
import com.chocolate.repository.mappers.toTopicDto
import com.chocolate.usecases.repositories.SavedLaterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedLaterRepositoryImpl @Inject constructor(
    private val savedLaterDataSource: SavedLaterDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val memberDataSource: MemberDataSource
) : SavedLaterRepository {

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

    override suspend fun getSavedLaterMessages(): Flow<List<Message>> {
        return savedLaterDataSource.getSavedLaterMessages(
            getCurrentOrganizationName(),
            getCurrentMember().id
        ).toMessage()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun saveMessage(message: Message) {
        savedLaterDataSource.addSavedLaterMessage(
            getCurrentOrganizationName(),
            message.toMessageDto()
        )
    }

    override suspend fun deleteSavedLaterMessageById(savedLaterMessageId: String) {
        savedLaterDataSource.deleteSavedLaterMessageById(
            getCurrentOrganizationName(),
            getCurrentMember().id,
            savedLaterMessageId
        )
    }

    override suspend fun getSavedTopics(): Flow<List<Topic>> {
        return savedLaterDataSource.getSavedTopics(
            getCurrentOrganizationName(),
            getCurrentMember().id
        ).toEntity()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun saveTopic(topic: Topic) {
        savedLaterDataSource.addSavedTopic(
            getCurrentOrganizationName(),
            topic.toTopicDto(),
            getCurrentMember().id,
        )
    }

    override suspend fun deleteSavedTopicById(topicId: String) {
        savedLaterDataSource.deleteSavedTopicById(
            getCurrentOrganizationName(),
            getCurrentMember().id,
            topicId
        )
    }
}