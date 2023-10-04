package com.chocolate.usecases.repositories

import com.chocolate.entities.entity.Message
import com.chocolate.entities.entity.Topic
import kotlinx.coroutines.flow.Flow

interface SavedLaterRepository {

    suspend fun getSavedTopics(): Flow<List<Topic>>

    suspend fun saveTopic(topic: Topic)

    suspend fun deleteSavedTopicById(topicId: String)

    suspend fun getSavedLaterMessages(): Flow<List<Message>>

    suspend fun saveMessage(message: Message)

    suspend fun deleteSavedLaterMessageById(savedLaterMessageId: String)
}