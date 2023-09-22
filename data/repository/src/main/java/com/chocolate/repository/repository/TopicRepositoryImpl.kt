package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.topic.Topic
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.realtime.TopicDataSource
import com.chocolate.repository.mappers.topic.toTopic
import com.chocolate.repository.mappers.topic.toTopicDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val topicDataSource: TopicDataSource,
    private val dataStore: PreferencesDataSource
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
