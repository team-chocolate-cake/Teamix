package com.chocolate.repository.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.chocolate.entities.messages.Message
import com.chocolate.entities.messages.Topic
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.realtime.TopicDataSource
import com.chocolate.repository.datastore.realtime.model.TopicDto
import com.chocolate.repository.datastore.realtime.model.toEntity
import com.chocolate.repository.utils.getCurrentTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import repositories.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val dataSource: TopicDataSource,
    private val dataStore: PreferencesDataSource
) : TopicRepository {


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createTopic(channelName: String, topicContent: String, senderId: String) {
        dataSource.createTopic(
            channelName = channelName,
            topic = TopicDto(
                senderId = senderId,
                content = topicContent,
                sentTime = getCurrentTime()
            ),
            //organization name from datastore
            organizationName = "teamixOrganization"
        )
    }

    override suspend fun getTopicsInAChannel(channelId: String): Flow<List<Topic>> {
       val result =dataSource.getTopicsInAChannel(
            channelId = channelId,
            organizationName = "teamixOrganization"
        ).map {
           it.map {topic ->
               topic.toEntity()
           }
       }
        result.collectLatest {
            Log.i("DEBUG",it.toString())
        }
        return result
    }

    override suspend fun sendMessage(message: Message, channelId: Int, organizationName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessages(channelId: Int): Flow<List<Message>> {
        TODO("Not yet implemented")
    }

}

//    override suspend fun getTopicMessages(channelId: String, topicId: String){
//        dataSource.getMessagesInATopic(channelId = "CHANNEL", topicId = "4T6DWHFRyuNx0gqI3p9S")
//            .collectLatest {
//                Log.i("TAG", it.toString())
//            }
//    }
