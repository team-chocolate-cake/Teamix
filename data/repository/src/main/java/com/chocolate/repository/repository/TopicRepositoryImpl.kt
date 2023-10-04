package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.entity.Topic
import com.chocolate.repository.datasource.local.PreferencesDataSource
import com.chocolate.repository.datasource.remote.TopicDataSource
import com.chocolate.repository.mappers.toTopic
import com.chocolate.repository.mappers.toTopicDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.chocolate.usecases.repositories.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val topicDataSource: TopicDataSource,
    private val dataStore: PreferencesDataSource,
) : TopicRepository {
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
}
