package com.chocolate.remote.data_source

import com.chocolate.remote.api.DraftService
import com.chocolate.remote.api.MessageService
import com.chocolate.remote.wrapApiCall
import com.chocolate.repository.datastore.remote.MessagesRemoteDataSource
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
import javax.inject.Inject

class MessagesRetrofitDataSource @Inject constructor(
    private val messageService: MessageService,
    private val draftService: DraftService,
) : MessagesRemoteDataSource {

    override suspend fun getDrafts(): DraftsDto {
        return wrapApiCall { draftService.getDrafts() }
    }

    override suspend fun createDraft(
        type: String,
        recipients: Int,
        topic: String,
        content: String,
    ): BaseDraftResponse {
        return wrapApiCall { draftService.createDraft(type, recipients, topic, content) }
    }

    override suspend fun editDraft(
        type: String,
        recipients: String,
        topic: String,
        content: String
    ): BaseDraftResponse {
        return wrapApiCall { draftService.editDraft(type, recipients, topic, content) }
    }

    override suspend fun deleteDraft(id: Int): BaseDraftResponse {
        return wrapApiCall { draftService.deleteDraft(id) }
    }

    override suspend fun sendStreamMessage(
        type: String,
        recipients: String,
        topic: String,
        content: String
    ): SendMessageDto {
        return wrapApiCall {
            messageService.sendStreamMessage(type, recipients, topic, content)
        }
    }

    override suspend fun sendDirectMessage(
        type: String,
        recipients: String,
        content: String,
    ): SendMessageDto {
        return wrapApiCall {
            messageService.sendDirectMessage(type, recipients, content)
        }
    }

    override suspend fun uploadFile(file: MultipartBody.Part): FileRemoteDto {
        return wrapApiCall {
            messageService.uploadFile(file)
        }
    }

    override suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String,
    ): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.editMessage(
                messageId,
                content,
                topic
            )
        }
    }

    override suspend fun deleteMessage(messageId: Int): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.deleteMessage(messageId)
        }
    }

    override suspend fun getMessages(
        anchor: String?,
        numBefore: Int,
        numAfter: Int,
    ): MessagesRemoteDto {
        return wrapApiCall { messageService.getMessages(numBefore, numAfter) }
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
    ): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.addEmojiReaction(messageId, emojiName)
        }
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
    ): DefaultMessageRemoteDto {
        return wrapApiCall { messageService.deleteEmojiReaction(messageId, emojiName) }
    }

    override suspend fun renderMessage(content: String): RenderMessageDto {
        return wrapApiCall {
            messageService.renderMessage(content)
        }
    }

    override suspend fun fetchSingleMessage(messageId: Int): SingleMessageDto {
        return wrapApiCall {
            messageService.fetchSingleMessage(messageId)
        }
    }

    override suspend fun checkIfMessagesMatchNarrow(
        messagesIds: String,
        narrow: String
    ): MatchNarrowDto {
        return wrapApiCall {
            messageService.checkIfMessagesMatchNarrow(messagesIds, narrow)
        }
    }

    override suspend fun getMessagesEditHistory(messageId: Int): MessageEditHistoryDto {
        return wrapApiCall {
            messageService.getMessagesEditHistory(messageId)
        }
    }

    override suspend fun markAllMessagesAsRead(): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.markAllMessagesAsRead()
        }
    }

    override suspend fun markStreamAsRead(steamId: Int): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.markStreamAsRead(steamId)
        }
    }

    override suspend fun markTopicAsRead(
        steamId: Int,
        topicName: String
    ): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.markTopicAsRead(steamId, topicName)
        }
    }

    override suspend fun getMessageReadReceipts(messageId: Int): MessageReadReceiptsDto {
        return wrapApiCall {
            messageService.getMessageReadReceipts(messageId)
        }
    }
}