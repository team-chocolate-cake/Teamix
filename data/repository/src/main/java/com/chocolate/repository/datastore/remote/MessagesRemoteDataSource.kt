package com.chocolate.repository.datastore.remote

import com.chocolate.repository.model.dto.draft.response.BaseDraftResponse
import com.chocolate.repository.model.dto.draft.response.DraftsDto
import com.chocolate.repository.model.dto.message.response.DefaultMessageRemoteDto
import com.chocolate.repository.model.dto.message.response.FileRemoteDto
import com.chocolate.repository.model.dto.message.response.MatchNarrowDto
import com.chocolate.repository.model.dto.message.response.MessageEditHistoryDto
import com.chocolate.repository.model.dto.message.response.MessageReadReceiptsDto
import com.chocolate.repository.model.dto.message.response.MessagesRemoteDto
import com.chocolate.repository.model.dto.message.response.RenderMessageDto
import com.chocolate.repository.model.dto.message.response.SendMessageDto
import com.chocolate.repository.model.dto.message.response.SingleMessageDto
import okhttp3.MultipartBody

interface MessagesRemoteDataSource {

    suspend fun getDrafts(): DraftsDto

    suspend fun deleteDraft(id: Int): BaseDraftResponse

    suspend fun createDraft(
        type: String,
        recipients: Int,
        topic: String,
        content: String,
    ): BaseDraftResponse

    suspend fun editDraft(
        type: String,
        recipients: String,
        topic: String,
        content: String
    ): BaseDraftResponse

    suspend fun sendStreamMessage(
        type: String,
        recipients: String,
        topic: String,
        content: String
    ): SendMessageDto

    suspend fun sendDirectMessage(
        type: String,
        recipients: String,
        content: String,
    ): SendMessageDto

    suspend fun uploadFile(file: MultipartBody.Part): FileRemoteDto

    suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String = ""
    ): DefaultMessageRemoteDto

    suspend fun deleteMessage(messageId: Int): DefaultMessageRemoteDto

    suspend fun getMessages(
        anchor: String?,
        numBefore: Int,
        numAfter: Int
    ): MessagesRemoteDto

    suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
    ): DefaultMessageRemoteDto

    suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String
    ): DefaultMessageRemoteDto

    suspend fun renderMessage(
        content: String,
    ): RenderMessageDto

    suspend fun fetchSingleMessage(
        messageId: Int
    ): SingleMessageDto

    suspend fun checkIfMessagesMatchNarrow(
        messagesIds: String,
        narrow: String
    ): MatchNarrowDto

    suspend fun getMessagesEditHistory(
        messageId: Int
    ): MessageEditHistoryDto

    suspend fun markAllMessagesAsRead(): DefaultMessageRemoteDto

    suspend fun markStreamAsRead(
        steamId: Int
    ): DefaultMessageRemoteDto

    suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): DefaultMessageRemoteDto

    suspend fun getMessageReadReceipts(
        messageId: Int
    ): MessageReadReceiptsDto
}