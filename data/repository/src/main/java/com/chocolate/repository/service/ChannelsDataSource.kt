package com.chocolate.repository.service

import com.chocolate.repository.dto.channels.response.AllStreamsDto
import com.chocolate.repository.dto.channels.response.AllSubscribersDto
import com.chocolate.repository.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.dto.channels.response.StreamsByIdDto
import com.chocolate.repository.dto.channels.response.StreamsIdDto
import com.chocolate.repository.dto.channels.response.SubscribeToStreamDto
import com.chocolate.repository.dto.channels.response.SubscribedStreamDto
import com.chocolate.repository.dto.channels.response.SubscriptionSettingsDto
import com.chocolate.repository.dto.channels.response.SubscriptionStatusDto
import com.chocolate.repository.dto.channels.response.TopicsInStreamDto
import com.chocolate.repository.dto.channels.response.UnsubscribeFromStreamDto
import retrofit2.Response

interface ChannelsDataSource {
    suspend fun getUserSubscriptions(includeSubscribers: Boolean = false): Response<SubscribedStreamDto>

    suspend fun addSubscribesToStream(
        subscribeToStream: String,
        principals: List<String>? = null,
        authorizationErrorsFatal: Boolean = true,
        announce: Boolean = false,
        inviteOnly: Boolean = false,
        isWebPublic: Boolean = false,
        historyPublicToSubscribers: Boolean = false,
        streamPostPolicy: Int? = null,
        messageRetentionDays: String? = null,
        canRemoveSubscribersGroupId: Int? = null,
    ): Response<SubscribeToStreamDto>

    suspend fun deleteSubscriberFromStream(
        subscriptions: String,
        principals: List<String>? = null,
    ): Response<UnsubscribeFromStreamDto>

    suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int,
    ): Response<SubscriptionStatusDto>

    suspend fun getAllSubscribers(streamId: Int): Response<AllSubscribersDto>

    suspend fun updateSubscriptionSettings(
        subscriptionData: String
    ): Response<SubscriptionSettingsDto>

    suspend fun getAllStreams(
        includePublic: Boolean = true,
        includeWebPublic: Boolean = false,
        includeSubscribed: Boolean = true,
        includeAllActive: Boolean = false,
        includeDefault: Boolean = false,
        includeOwnerSubscribed: Boolean = false,
    ): Response<AllStreamsDto>

    suspend fun getStreamById(
        streamId: Int
    ): Response<StreamsByIdDto>

    suspend fun getStreamId(
        stream: String
    ): Response<StreamsIdDto>

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
    ): Response<DefaultStreamDto>

    suspend fun archiveStream(
        streamId: Int
    ): Response<DefaultStreamDto>

    suspend fun getTopicsInStream(
        streamId: Int
    ): Response<TopicsInStreamDto>

    suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int? = null,
        stream: String? = null,
    ): Response<DefaultStreamDto>

    suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int,
    ): Response<DefaultStreamDto>

    suspend fun deleteTopic(
        streamId: Int,
        topicName: String
    ): Response<DefaultStreamDto>

    suspend fun addDefaultStream(
        streamId: Int,
    ): Response<DefaultStreamDto>

    suspend fun deleteDefaultStream(
        streamId: Int
    ): Response<DefaultStreamDto>
}
