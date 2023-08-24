package com.chocolate.remote

import com.chocolate.entities.user.User
import com.chocolate.remote.channels.service.ChannelsService
import com.chocolate.remote.drafts.service.DraftService
import com.chocolate.remote.messages.service.MessageService
import com.chocolate.remote.scheduled_message.service.ScheduledMessageService
import com.chocolate.remote.server_and_organizations.service.OrganizationService
import com.chocolate.remote.users.service.UsersService
import com.chocolate.repository.model.dto.channels.response.AllStreamsDto
import com.chocolate.repository.model.dto.channels.response.AllSubscribersDto
import com.chocolate.repository.model.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.model.dto.channels.response.StreamsByIdDto
import com.chocolate.repository.model.dto.channels.response.StreamsIdDto
import com.chocolate.repository.model.dto.channels.response.SubscribeToStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscribedStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscriptionStatusDto
import com.chocolate.repository.model.dto.channels.response.TopicsInStreamDto
import com.chocolate.repository.model.dto.channels.response.UnsubscribeFromStreamDto
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
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.model.dto.server_and_organizations.response.DefaultOrganizationDto
import com.chocolate.repository.model.dto.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.model.dto.server_and_organizations.response.ServerSettingsDto
import com.chocolate.repository.model.dto.users.response.FetchApiKeyDto
import com.chocolate.repository.model.dto.users.response.StatusUserRemoteDto
import com.chocolate.repository.service.remote.RemoteDataSource
import com.chocolate.repository.utils.HttpStatusCodes
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLException

class RetrofitDataSource @Inject constructor(
    private val channelsService: ChannelsService,
    private val draftService: DraftService,
    private val messageService: MessageService,
    private val scheduledMessageService: ScheduledMessageService,
    private val organizationService: OrganizationService,
    private val userService: UsersService,
    ) : RemoteDataSource {

    override suspend fun getSubscribedChannels(): SubscribedStreamDto {
        return wrapApiCall { channelsService.getSubscribedChannels() }
    }

    override suspend fun subscribeToChannels(
        channelName: String,
        usersId: List<Int>
    ): SubscribeToStreamDto {
        return wrapApiCall { channelsService.subscribeToChannels(channelName, usersId) }
    }

    override suspend fun unsubscribeFromChannels(
        channelsName: List<String>
    ): UnsubscribeFromStreamDto {
        return wrapApiCall { channelsService.unsubscribeFromChannels(channelsName) }
    }

    override suspend fun getSubscriptionStatus(
        userId: Int, channelId: Int
    ): SubscriptionStatusDto {
        return wrapApiCall { channelsService.getSubscriptionStatus(userId, channelId) }
    }

    override suspend fun getSubscribersInChannel(channelId: Int): AllSubscribersDto {
        return wrapApiCall { channelsService.getSubscribersInChannel(channelId) }
    }

    override suspend fun getChannels(): AllStreamsDto {
        return wrapApiCall { channelsService.getChannels() }
    }

    override suspend fun getChannelById(channelId: Int): StreamsByIdDto {
        return wrapApiCall { channelsService.getChannelById(channelId) }
    }

    override suspend fun getChannelIdByName(channelName: String): StreamsIdDto {
        return wrapApiCall { channelsService.getChannelIdByName(channelName) }
    }

    override suspend fun updateStream(
        streamId: Int,
        description: String?,
        newName: String?,
        isPrivate: Boolean?,
        isWebPublic: Boolean?,
        historyPublicToSubscribers: Boolean?,
        streamPostPolicy: Int?,
        messageRetentionDays: String?,
        canRemoveSubscribersGroupId: Int?
    ): DefaultStreamDto {
        return wrapApiCall {
            channelsService.updateChannel(
                streamId,
                description,
                newName,
                isPrivate,
                isWebPublic,
                historyPublicToSubscribers,
                streamPostPolicy,
                messageRetentionDays,
                canRemoveSubscribersGroupId
            )
        }
    }

    override suspend fun archiveChannel(channelId: Int): DefaultStreamDto {
        return wrapApiCall { channelsService.archiveChannel(channelId) }
    }

    override suspend fun getTopicsInChannel(channelId: Int): TopicsInStreamDto {
        return wrapApiCall { channelsService.getTopicsInChannel(channelId) }
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
    ): DefaultStreamDto {
        return wrapApiCall { channelsService.setTopicMuting(topic, status, streamId) }
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): DefaultStreamDto {
        return wrapApiCall {
            channelsService.updatePersonalPreferenceTopic(streamId, topic, visibilityPolicy)
        }
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): DefaultStreamDto {
        return wrapApiCall { channelsService.deleteTopic(channelId, topicName) }
    }

    override suspend fun addDefaultStream(channelId: Int): DefaultStreamDto {
        return wrapApiCall {
            channelsService.addDefaultChannel(channelId)
        }
    }

    override suspend fun deleteDefaultStream(channelId: Int): DefaultStreamDto {
        return wrapApiCall { channelsService.deleteDefaultChannel(channelId) }
    }

    override suspend fun getDrafts(): DraftsDto {
        return wrapApiCall { draftService.getDrafts() }
    }

    override suspend fun createDraft(
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): BaseDraftResponse {
        return wrapApiCall { draftService.createDraft(id, type, to, topic, content, timestamp) }
    }

    override suspend fun editDraft(
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): BaseDraftResponse {
        return wrapApiCall { draftService.editDraft(id, type, to, topic, content, timestamp) }
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
            messageService.sendStreamMessage(type, to, topic, content, queueId, localId)
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
            messageService.sendDirectMessage(type, to, content, queueId, localId)
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
                topic,
                propagateMode,
                sendNotificationToOldThread,
                sendNotificationToNewThread
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
        includeAnchor: Boolean,
        numBefore: Int,
        numAfter: Int,
        narrow: List<String>?,
        clientGravatar: Boolean,
        applyMarkdown: Boolean
    ): MessagesRemoteDto {
        return wrapApiCall {
            messageService.getMessages(
                anchor,
                includeAnchor,
                numBefore,
                numAfter,
                narrow,
                clientGravatar,
                applyMarkdown
            )
        }
    }

    override suspend fun addEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.addEmojiReaction(messageId, emojiName, emojiCode, reactionType)
        }
    }

    override suspend fun deleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): DefaultMessageRemoteDto {
        return wrapApiCall {
            messageService.deleteEmojiReaction(messageId, emojiName, emojiCode, reactionType)
        }
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

    override suspend fun updateMessageFlags(
        messages: List<Int>,
        op: String,
        flag: String
    ): PersonalMessageFlagsDto {
        return wrapApiCall {
            messageService.updateMessageFlags(messages, op, flag)
        }
    }

    override suspend fun updatePersonalMessageFlagsForNarrow(
        anchor: String,
        numBefore: Int,
        numAfter: Int,
        includeAnchor: Boolean,
        narrow: String,
        op: String,
        flag: String
    ): PersonalMessageForNarrowDto {
        return wrapApiCall {
            messageService.updatePersonalMessageFlagsForNarrow(
                anchor,
                numBefore,
                numAfter,
                includeAnchor,
                narrow,
                op,
                flag
            )
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

    override suspend fun getScheduledMessages(): ScheduledMessagesDto {
        return wrapApiCall { scheduledMessageService.getScheduledMessages() }
    }

    override suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): BaseScheduledMessageResponse {
        return wrapApiCall {
            scheduledMessageService.createScheduledMessage(
                type,
                to,
                content,
                topic,
                scheduledDeliveryTimestamp
            )
        }
    }

    override suspend fun editScheduledMessage(
        id: Int,
        type: String?,
        to: Any?,
        content: String?,
        topic: String?,
        scheduledDeliveryTimestamp: Long?
    ): BaseScheduledMessageResponse {
        return wrapApiCall {
            scheduledMessageService.editScheduledMessage(
                id,
                type,
                to,
                content,
                topic,
                scheduledDeliveryTimestamp
            )
        }
    }

    override suspend fun deleteScheduledMessage(id: Int): BaseScheduledMessageResponse {
        return wrapApiCall {
            scheduledMessageService.deleteScheduledMessage(id)
        }
    }

    override suspend fun getServerSettings(): ServerSettingsDto {
        return wrapApiCall {
            organizationService.getServerSettings()
        }
    }

    override suspend fun getLinkifiers(): LinkifiersDto {
        return wrapApiCall {
            organizationService.getLinkifiers()
        }
    }

    override suspend fun addLinkifiers(
        pattern: String,
        url: String
    ): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.addLinkifiers(pattern, url)
        }
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.updateLinkifiers(filterId, pattern, url)
        }
    }

    override suspend fun deleteLinkifiers(filterId: Int): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.deleteLinkifiers(filterId)
        }
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.addCodePlayGround(name, language, url)
        }
    }

    override suspend fun deleteCodePlayground(playGroundId: Int): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.deleteCodePlayground(playGroundId)
        }
    }

    override suspend fun getAllCustomEmojis(): CustomEmojiDto {
        return wrapApiCall {
            organizationService.getAllCustomEmojis()
        }
    }

    override suspend fun addCustomEmoji(emojiName: String): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.addCustomEmoji(emojiName)
        }
    }

    override suspend fun deactivateCustomEmoji(emojiName: String): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.deactivateCustomEmoji(emojiName)
        }
    }

    override suspend fun getAllCustomProfileFields(): CustomProfileFieldsDto {
        return wrapApiCall {
            organizationService.getAllCustomProfileFields()
        }
    }

    override suspend fun reorderCustomProfileFields(order: String): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.reorderCustomProfileFields(order)
        }
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.createCustomProfileField(name, hint, fieldType)
        }
    }

    override suspend fun getAllUsers() = wrapApiCall {
        userService.getAllUsers()
    }

    override suspend fun getOwnUser() = wrapApiCall {
        userService.getOwnUser()
    }

    override suspend fun getUserStatus(): StatusUserRemoteDto {
        return wrapApiCall { userService.getUserStatus() }
    }

    override suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ) = wrapApiCall {
        userService.getUserById(userId, clientGravatar, includeCustomProfileFields)
    }

    override suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ) = wrapApiCall {
        userService.getUserByEmail(email, clientGravatar, includeCustomProfileFields)
    }

    override suspend fun updateUserById(
        id: Int,
        fullName: String?,
        role: Int?,
    ) = wrapApiCall {
        userService.updateUserById(id, fullName, role)
    }

    override suspend fun deactivateUserAccount(id: Int) = wrapApiCall {
        userService.deactivateUser(id)
    }

    override suspend fun reactivateUserAccount(id: Int) = wrapApiCall {
        userService.reactivateUser(id)
    }

    override suspend fun deactivateOwnUserAccount() = wrapApiCall {
        userService.deactivateOwnUser()
    }

    override suspend fun getUserPresence(email: String) = wrapApiCall {
        userService.getUserPresence(email)
    }

    override suspend fun getRealmPresence() = wrapApiCall {
        userService.getRealmPresence()
    }

    override suspend fun getAttachments() = wrapApiCall {
        userService.getAttachments()
    }

    override suspend fun deleteAttachment(attachmentId: Int) = wrapApiCall {
        userService.deleteAttachment(attachmentId)
    }

    override suspend fun updateSettings(user: User) = wrapApiCall {
        userService.updateSettings(user.fullName,user.email)
    }

    override suspend fun getUserGroups() = wrapApiCall {
        userService.getUserGroups()
    }

    override suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ) = wrapApiCall {
        userService.createUserGroup(name, description, members)
    }

    override suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ) = wrapApiCall {
        userService.updateUserGroup(userGroupId, name, description)
    }

    override suspend fun removeUserGroup(userGroupId: Int) = wrapApiCall {
        userService.removeUserGroup(userGroupId)
    }

    override suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ) = wrapApiCall {
        userService.updateUserGroupMembers(id, add, delete)
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ) = wrapApiCall {
        userService.updateUserGroupSubgroups(userGroupId, add, delete)
    }

    override suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ) = wrapApiCall {
        userService.getUserMembership(groupId, userId, directMemberOnly)
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ) = wrapApiCall {
        userService.getUserGroupMemberships(groupId, directMemberOnly)
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ) = wrapApiCall {
        userService.getSubgroupsOfUserGroup(id, directSubgroupOnly)
    }

    override suspend fun muteUser(mutedUserId: Int) = wrapApiCall {
        userService.muteUser(mutedUserId)
    }

    override suspend fun unmuteUser(mutedUserId: Int) = wrapApiCall {
        userService.unMuteUser(mutedUserId)
    }

    override suspend fun fetchApiKey(userName: String, password: String): FetchApiKeyDto {
        return wrapApiCall {
            userService.fetchApiKey(userName, password)
        }
    }
}