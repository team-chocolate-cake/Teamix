package com.chocolate.repository.service.remote

import com.chocolate.repository.dto.remote.channels.response.AllStreamsDto
import com.chocolate.repository.dto.remote.channels.response.AllSubscribersDto
import com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto
import com.chocolate.repository.dto.remote.channels.response.StreamsByIdDto
import com.chocolate.repository.dto.remote.channels.response.StreamsIdDto
import com.chocolate.repository.dto.remote.channels.response.SubscribeToStreamDto
import com.chocolate.repository.dto.remote.channels.response.SubscribedStreamDto
import com.chocolate.repository.dto.remote.channels.response.SubscriptionSettingsDto
import com.chocolate.repository.dto.remote.channels.response.SubscriptionStatusDto
import com.chocolate.repository.dto.remote.channels.response.TopicsInStreamDto
import com.chocolate.repository.dto.remote.channels.response.UnsubscribeFromStreamDto
import retrofit2.Response

interface ChannelsDataSource {
    suspend fun getUserSubscriptions(includeSubscribers: Boolean = false): Response<com.chocolate.repository.dto.remote.channels.response.SubscribedStreamDto>

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
    ): Response<com.chocolate.repository.dto.remote.channels.response.SubscribeToStreamDto>

    suspend fun deleteSubscriberFromStream(
        subscriptions: String,
        principals: List<String>? = null,
    ): Response<com.chocolate.repository.dto.remote.channels.response.UnsubscribeFromStreamDto>

    suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int,
    ): Response<com.chocolate.repository.dto.remote.channels.response.SubscriptionStatusDto>

    suspend fun getAllSubscribers(streamId: Int): Response<com.chocolate.repository.dto.remote.channels.response.AllSubscribersDto>

    suspend fun updateSubscriptionSettings(
        subscriptionData: String
    ): Response<com.chocolate.repository.dto.remote.channels.response.SubscriptionSettingsDto>

    suspend fun getAllStreams(
        includePublic: Boolean = true,
        includeWebPublic: Boolean = false,
        includeSubscribed: Boolean = true,
        includeAllActive: Boolean = false,
        includeDefault: Boolean = false,
        includeOwnerSubscribed: Boolean = false,
    ): Response<com.chocolate.repository.dto.remote.channels.response.AllStreamsDto>

    suspend fun getStreamById(
        streamId: Int
    ): Response<com.chocolate.repository.dto.remote.channels.response.StreamsByIdDto>

    suspend fun getStreamId(
        stream: String
    ): Response<com.chocolate.repository.dto.remote.channels.response.StreamsIdDto>

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
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto>

    suspend fun archiveStream(
        streamId: Int
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto>

    suspend fun getTopicsInStream(
        streamId: Int
    ): Response<com.chocolate.repository.dto.remote.channels.response.TopicsInStreamDto>

    suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int? = null,
        stream: String? = null,
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto>

    suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int,
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto>

    suspend fun deleteTopic(
        streamId: Int,
        topicName: String
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto>

    suspend fun addDefaultStream(
        streamId: Int,
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto>

    suspend fun deleteDefaultStream(
        streamId: Int
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto>
}
