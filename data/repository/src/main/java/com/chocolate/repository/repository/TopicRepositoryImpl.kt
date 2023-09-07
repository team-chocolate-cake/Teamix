package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.channel.Channel
import com.chocolate.entities.messages.Topic
import com.chocolate.repository.datastore.realtime.TopicDataSource
import com.chocolate.repository.datastore.realtime.model.TopicDto
import com.chocolate.repository.utils.getCurrentTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(private val dataSource: TopicDataSource) :
    TopicRepository {


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createTopic(channelName: String, topicContent: String, senderId: String) {
        dataSource.createTopic(
            channelName = channelName,
            topic = TopicDto(senderId, topicContent,getCurrentTime()),
            organizationName = "teamixOrganization"
        )
    }

    override suspend fun getTopicsInAChannel(channelId: String): Flow<List<Topic>> {
//        return dataSource.getTopicsInAChannel(channelName = channelId,"").map {
//            it.map {
//                it
//            }
//        }
    TODO()
    }
}