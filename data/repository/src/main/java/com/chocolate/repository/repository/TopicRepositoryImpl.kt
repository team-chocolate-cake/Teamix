package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.EmptyMemberIdException
import com.chocolate.entities.util.EmptyOrganizationNameException
import com.chocolate.repository.datasource.local.PreferencesDataSource
import com.chocolate.repository.datasource.remote.MemberDataSource
import com.chocolate.repository.datasource.remote.TopicDataSource
import com.chocolate.repository.mappers.toEntity
import com.chocolate.repository.mappers.toTopic
import com.chocolate.repository.mappers.toTopicDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val topicDataSource: TopicDataSource,
    private val dataStore: PreferencesDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val memberDataSource: MemberDataSource
    ) : TopicRepository {
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
    override suspend fun createTopic(channelId: String, topic: Topic): String {
        return topicDataSource.createTopic(
            channelId,
            topic.toTopicDto(),
            dataStore.getCurrentOrganizationName() ?: "teamixOrganization"
        )
    }

    override suspend fun getTopicsInChannel(
        channelId: String,
    ): Flow<List<Topic>> {
        return topicDataSource.getTopicsInAChannel(
            channelId = channelId,
            organizationName = dataStore.getCurrentOrganizationName() ?: "teamixOrganization"
        ).map { it.toTopic() }
    }

    override suspend fun getSavedTopics(): Flow<List<Topic>> {
        return topicDataSource.getSavedTopics(
            getCurrentOrganizationName(),
            getCurrentMember().id
        ).toEntity()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun saveTopic(topic: Topic) {
        topicDataSource.addSavedTopic(getCurrentOrganizationName(),
            topic.toTopicDto(),
            getCurrentMember().id,
        )
    }

    override suspend fun deleteSavedTopicById(topicId: String) {
        topicDataSource.deleteSavedTopicById(
            getCurrentOrganizationName(),
            getCurrentMember().id,
            topicId
        )
    }
}
