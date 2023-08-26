package com.chocolate.repository.datastore.remote

import com.chocolate.repository.model.dto.draft.response.BaseDraftResponse
import com.chocolate.repository.model.dto.draft.response.DraftsDto
import com.chocolate.repository.model.dto.message.response.DefaultMessageRemoteDto
import com.chocolate.repository.model.dto.message.response.FileRemoteDto
import com.chocolate.repository.model.dto.message.response.MatchNarrowDto
import com.chocolate.repository.model.dto.message.response.MessageEditHistoryDto
import com.chocolate.repository.model.dto.message.response.MessageReadReceiptsDto
import com.chocolate.repository.model.dto.message.response.MessagesRemoteDto
import com.chocolate.repository.model.dto.message.response.PersonalMessageFlagsDto
import com.chocolate.repository.model.dto.message.response.PersonalMessageForNarrowDto
import com.chocolate.repository.model.dto.message.response.RenderMessageDto
import com.chocolate.repository.model.dto.message.response.SendMessageDto
import com.chocolate.repository.model.dto.message.response.SingleMessageDto
import com.chocolate.repository.model.dto.scheduled_message.response.BaseScheduledMessageResponse
import com.chocolate.repository.model.dto.scheduled_message.response.ScheduledMessagesDto
import okhttp3.MultipartBody

interface MessagesRemoteDataSource {

    suspend fun getDrafts(): DraftsDto

    suspend fun deleteDraft(id: Int): BaseDraftResponse

    suspend fun createDraft(
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): BaseDraftResponse

    suspend fun editDraft(
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): BaseDraftResponse

    suspend fun getScheduledMessages(): ScheduledMessagesDto

    suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): BaseScheduledMessageResponse

    suspend fun editScheduledMessage(
        id: Int,
        type: String? = null,
        to: Any? = null,
        content: String? = null,
        topic: String? = null,
        scheduledDeliveryTimestamp: Long? = null
    ): BaseScheduledMessageResponse

    suspend fun deleteScheduledMessage(id: Int): BaseScheduledMessageResponse
    suspend fun sendStreamMessage(
        type: String,
        to: Any,
        topic: String,
        content: String,
        queueId: String?,
        localId: String?,
    ): SendMessageDto

    suspend fun sendDirectMessage(
        type: String,
        to: Any,
        content: String,
        queueId: String?,
        localId: String?,
    ): SendMessageDto

    suspend fun uploadFile(file: MultipartBody.Part): FileRemoteDto

    suspend fun editMessage(
        messageId: Int,
        content: String,
        topic: String = "",
        propagateMode: String = "change_one",
        sendNotificationToOldThread: Boolean = false,
        sendNotificationToNewThread: Boolean = true
    ): DefaultMessageRemoteDto

    suspend fun deleteMessage(messageId: Int): DefaultMessageRemoteDto

    suspend fun getMessages(
        anchor: String?,
        includeAnchor: Boolean = true,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>? = null,
        clientGravatar: Boolean = true,
        applyMarkdown: Boolean = true
    ): MessagesRemoteDto

    suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): DefaultMessageRemoteDto

    suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): DefaultMessageRemoteDto

    suspend fun renderMessage(
        content: String,
    ): RenderMessageDto

    suspend fun fetchSingleMessage(
        messageId: Int
    ): SingleMessageDto

    suspend fun checkIfMessagesMatchNarrow(
        msg_ids: String,
        narrow: String
    ): MatchNarrowDto

    suspend fun getMessagesEditHistory(
        messageId: Int
    ): MessageEditHistoryDto

    suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String,
    ): PersonalMessageFlagsDto

    suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        includeAnchor: Boolean = true,
        narrow: String,
        op: String,
        flag: String
    ): PersonalMessageForNarrowDto

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