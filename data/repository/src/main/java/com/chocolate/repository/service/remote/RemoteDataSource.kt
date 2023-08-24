package com.chocolate.repository.service.remote

import com.chocolate.entities.user.User
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
import com.chocolate.repository.model.dto.users.response.MuteUserResponseDto
import com.chocolate.repository.model.dto.users.response.OwnerUserDto
import com.chocolate.repository.model.dto.users.response.ResponseStateDto
import com.chocolate.repository.model.dto.users.response.StatusUserRemoteDto
import com.chocolate.repository.model.dto.users.response.SubgroupsOfUserGroupDto
import com.chocolate.repository.model.dto.users.response.UserAttachmentsDto
import com.chocolate.repository.model.dto.users.response.UserDto
import com.chocolate.repository.model.dto.users.response.UserGroupMembershipsDto
import com.chocolate.repository.model.dto.users.response.UserGroupsDto
import com.chocolate.repository.model.dto.users.response.UserMembershipStateDto
import com.chocolate.repository.model.dto.users.response.UserSettingsDto
import com.chocolate.repository.model.dto.users.response.UserStateDto
import com.chocolate.repository.model.dto.users.response.UsersDto
import com.chocolate.repository.model.dto.users.response.UsersStateDto
import okhttp3.MultipartBody

interface RemoteDataSource{
    suspend fun getSubscribedChannels(): SubscribedStreamDto

    suspend fun subscribeToChannels(
        channelName: String,
        usersId: List<Int>
    ): SubscribeToStreamDto

    suspend fun unsubscribeFromChannels(channelsName: List<String>): UnsubscribeFromStreamDto

    suspend fun getSubscriptionStatus(userId: Int, channelId: Int): SubscriptionStatusDto

    suspend fun getSubscribersInChannel(channelId: Int): AllSubscribersDto

    suspend fun getChannels(): AllStreamsDto

    suspend fun getChannelById(
        channelId: Int
    ): StreamsByIdDto

    suspend fun getChannelIdByName(
        channelName: String
    ): StreamsIdDto

    suspend fun updateStream(
        streamId: Int,
        description: String? = null,
        newName: String? = null,
        isPrivate: Boolean? = null,
        isWebPublic: Boolean? = null,
        historyPublicToSubscribers: Boolean? = null,
        streamPostPolicy: Int? = null,
        messageRetentionDays: String? = null,
        canRemoveSubscribersGroupId: Int? = null,
    ): DefaultStreamDto

    suspend fun archiveChannel(
        channelId: Int
    ): DefaultStreamDto

    suspend fun getTopicsInChannel(
        channelId: Int
    ): TopicsInStreamDto

    suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int? = null,
    ): DefaultStreamDto

    suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int,
    ): DefaultStreamDto

    suspend fun deleteTopic(
        channelId: Int,
        topicName: String
    ): DefaultStreamDto

    suspend fun addDefaultStream(
        channelId: Int,
    ): DefaultStreamDto

    suspend fun deleteDefaultStream(
        channelId: Int
    ): DefaultStreamDto

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

    suspend fun getServerSettings(): ServerSettingsDto

    suspend fun getLinkifiers(): LinkifiersDto

    suspend fun addLinkifiers(
        pattern: String,
        url: String
    ): DefaultOrganizationDto

    suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): DefaultOrganizationDto

    suspend fun deleteLinkifiers(filterId: Int): DefaultOrganizationDto

    suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String,
    ): DefaultOrganizationDto

    suspend fun deleteCodePlayground(playGroundId: Int): DefaultOrganizationDto

    suspend fun getAllCustomEmojis(): CustomEmojiDto

    suspend fun addCustomEmoji(emojiName: String): DefaultOrganizationDto

    suspend fun deactivateCustomEmoji(emojiName: String): DefaultOrganizationDto

    suspend fun getAllCustomProfileFields(): CustomProfileFieldsDto

    suspend fun reorderCustomProfileFields(
        order: String
    ): DefaultOrganizationDto

    suspend fun createCustomProfileField(
        name: String = "",
        hint: String = "",
        fieldType: Int,
    ): DefaultOrganizationDto

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

    suspend fun getAllUsers(
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): UsersDto

    suspend fun getOwnUser(): OwnerUserDto

    suspend fun getUserStatus(): StatusUserRemoteDto

    suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): UserDto

    suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean = false,
        includeCustomProfileFields: Boolean = false
    ): UserDto

    suspend fun updateUserById(
        id: Int,
        fullName: String? = null,
        role: Int? = null
    ): ResponseStateDto

    suspend fun deactivateUserAccount(id: Int): ResponseStateDto

    suspend fun reactivateUserAccount(id: Int): ResponseStateDto

    suspend fun deactivateOwnUserAccount(): ResponseStateDto

    suspend fun getUserPresence(email: String): UserStateDto

    suspend fun getRealmPresence(): UsersStateDto

    suspend fun getAttachments(): UserAttachmentsDto

    suspend fun deleteAttachment(attachmentId: Int): ResponseStateDto

    suspend fun updateSettings(user: User): UserSettingsDto

    suspend fun getUserGroups(): UserGroupsDto

    suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): ResponseStateDto

    suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): ResponseStateDto

    suspend fun removeUserGroup(userGroupId: Int): ResponseStateDto

    suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): ResponseStateDto

    suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ): SubgroupsOfUserGroupDto

    suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): UserMembershipStateDto

    suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): UserGroupMembershipsDto

    suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): SubgroupsOfUserGroupDto

    suspend fun muteUser(mutedUserId: Int): MuteUserResponseDto

    suspend fun unmuteUser(mutedUserId: Int): MuteUserResponseDto

    suspend fun fetchApiKey(userName: String, password: String): FetchApiKeyDto

}