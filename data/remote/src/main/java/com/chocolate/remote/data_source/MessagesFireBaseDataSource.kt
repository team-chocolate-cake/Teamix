package com.chocolate.remote.data_source

import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.remote.api.DraftService
import com.chocolate.remote.api.MessageService
import com.chocolate.remote.firebase.util.Constants
import com.chocolate.remote.firebase.util.getRandomId
import com.chocolate.remote.firebase.util.tryToExecuteSuspendCall
import com.chocolate.remote.wrapApiCall
import com.chocolate.repository.datastore.realtime.model.MessageDto
import com.chocolate.repository.datastore.remote.MessagesRemoteDataSource
import com.chocolate.repository.model.dto.draft.response.BaseDraftResponse
import com.chocolate.repository.model.dto.draft.response.DraftsDto
import com.chocolate.repository.model.dto.message.response.DefaultMessageRemoteDto
import com.chocolate.repository.model.dto.message.response.FileRemoteDto
import com.chocolate.repository.model.dto.message.response.MatchNarrowDto
import com.chocolate.repository.model.dto.message.response.MessageEditHistoryDto
import com.chocolate.repository.model.dto.message.response.MessageReadReceiptsDto
import com.chocolate.repository.model.dto.message.response.RenderMessageDto
import com.chocolate.repository.model.dto.message.response.SendMessageDto
import com.chocolate.repository.model.dto.message.response.SingleMessageDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import okhttp3.MultipartBody
import javax.inject.Inject

class MessagesFireBaseDataSource @Inject constructor(
    private val messageService: MessageService,
    private val draftService: DraftService,
    private val firebaseFirestore: FirebaseFirestore,
) : MessagesRemoteDataSource {
    override suspend fun sendMessageInTopic(
        message: MessageDto,
        topicId: String,
        channelId: String,
        organizationName: String,
    ) {
        val messageId = getRandomId()
        val messageDto = MessageDto(
            id = messageId.toString(),
            content = message.content,
            userId = message.userId,
            senderName = message.senderName,
            senderImage = message.senderImage,
            timestamp = message.timestamp,
        )
        tryToExecuteSuspendCall {
            firebaseFirestore.collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .document(channelId)
                .collection(Constants.TOPICS)
                .document(topicId)
                .collection(Constants.MESSAGE)
                .document(messageId.toString())
                .set(messageDto)
                .await()
        }
    }

    override suspend fun getMessagesFromTopic(
        topicId: String,
        channelId: String,
        organizationName: String
    ): Flow<List<MessageDto>> {
        return callbackFlow {
            val listener = firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .document(channelId)
                .collection(Constants.TOPICS)
                .document(topicId)
                .collection(Constants.MESSAGE).addSnapshotListener { value, error ->
                    if (error != null)
                        throw TeamixException(error.message)
                    val messages = value?.toObjects<MessageDto>()
                    messages?.let { trySend(it) }
                }
            awaitClose { listener.remove() }
        }
    }


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
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): BaseDraftResponse {
        return wrapApiCall { draftService.editDraft(type, to, topic, content) }
    }

    override suspend fun deleteDraft(id: Int): BaseDraftResponse {
        return wrapApiCall { draftService.deleteDraft(id) }
    }

    override suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String?,
        localId: String?
    ): SendMessageDto {
        return wrapApiCall {
            messageService.sendStreamMessage(type, to, topic, content)
        }
    }

    override suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?
    ): SendMessageDto {
        return wrapApiCall {
            messageService.sendDirectMessage(type, to, content)
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
        propagateMode: String,
        sendNotificationToOldThread: Boolean,
        sendNotificationToNewThread: Boolean
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


    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.addEmojiReaction(messageId, emojiName)
        }
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
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
        msg_ids: String,
        narrow: String
    ): MatchNarrowDto {
        return wrapApiCall {
            messageService.checkIfMessagesMatchNarrow(msg_ids, narrow)
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