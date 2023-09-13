package com.chocolate.repository.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.draft.Draft
import com.chocolate.entities.messages.Message
import com.chocolate.repository.datastore.local.LocalDataSource
import com.chocolate.repository.datastore.remote.MessagesRemoteDataSource
import com.chocolate.repository.mappers.messages.toMessage
import com.chocolate.repository.mappers.messages.toMessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.MessagesRepository
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val messagesRemoteDataSource: MessagesRemoteDataSource,
    private val teamixLocalDataSource: LocalDataSource,
) : MessagesRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sendMessageInTopic(
        message: Message,
        topicId: String,
        channelId: String,
        organizationName: String
    ) {
        messagesRemoteDataSource.sendMessageInTopic(
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
        return messagesRemoteDataSource.getMessagesFromTopic(
            topicId,
            channelId,
            "teamixOrganization"
        ).map { it.toMessage() }
    }

    override suspend fun deleteMessage(messageId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getDrafts(): List<Draft> {
        TODO("Not yet implemented")
    }

    override suspend fun createDraft(
        type: String,
        recipients: Int,
        topic: String,
        content: String
    ): List<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDraft(id: Int) {
        TODO("Not yet implemented")
    }
//    override suspend fun getSavedMessages(): List<Message> {
//        return teamixLocalDataSource.getSavedMessages().map { it.toEntity() }
//    }
//
//    override suspend fun saveMessage(message: Message) {
//        teamixLocalDataSource.saveMessage(message.toLocalDto())
//    }

    override suspend fun deleteSavedMessageById(id: Int) {
        teamixLocalDataSource.deleteSavedMessageById(id)
    }
}