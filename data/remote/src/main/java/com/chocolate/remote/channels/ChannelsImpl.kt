package com.chocolate.remote.channels

import com.chocolate.remote.channels.service.ChannelsService
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
import com.chocolate.repository.service.remote.ChannelsDataSource
import retrofit2.Response
import javax.inject.Inject

class ChannelsImpl @Inject constructor(
    private val channelsService: ChannelsService
): ChannelsDataSource {
    override suspend fun getUserSubscriptions(
        includeSubscribers: Boolean
    ): Response<com.chocolate.repository.dto.remote.channels.response.SubscribedStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addSubscribesToStream(
        subscribeToStream: String,
        principals: List<String>?,
        authorizationErrorsFatal: Boolean,
        announce: Boolean,
        inviteOnly: Boolean,
        isWebPublic: Boolean,
        historyPublicToSubscribers: Boolean,
        streamPostPolicy: Int?,
        messageRetentionDays: String?,
        canRemoveSubscribersGroupId: Int?
    ): Response<com.chocolate.repository.dto.remote.channels.response.SubscribeToStreamDto> {
        TODO("Not yet implemented")
    }


    override suspend fun deleteSubscriberFromStream(
        subscriptions: String,
        principals: List<String>?
    ): Response<com.chocolate.repository.dto.remote.channels.response.UnsubscribeFromStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int
    ): Response<com.chocolate.repository.dto.remote.channels.response.SubscriptionStatusDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSubscribers(streamId: Int): Response<com.chocolate.repository.dto.remote.channels.response.AllSubscribersDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSubscriptionSettings(subscriptionData: String): Response<com.chocolate.repository.dto.remote.channels.response.SubscriptionSettingsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllStreams(
        includePublic: Boolean,
        includeWebPublic: Boolean,
        includeSubscribed: Boolean,
        includeAllActive: Boolean,
        includeDefault: Boolean,
        includeOwnerSubscribed: Boolean
    ): Response<com.chocolate.repository.dto.remote.channels.response.AllStreamsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getStreamById(streamId: Int): Response<com.chocolate.repository.dto.remote.channels.response.StreamsByIdDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getStreamId(stream: String): Response<com.chocolate.repository.dto.remote.channels.response.StreamsIdDto> {
        TODO("Not yet implemented")
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
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun archiveStream(streamId: Int): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopicsInStream(streamId: Int): Response<com.chocolate.repository.dto.remote.channels.response.TopicsInStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
        stream: String?
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTopic(streamId: Int, topicName: String): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addDefaultStream(streamId: Int): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDefaultStream(streamId: Int): Response<com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto> {
        TODO("Not yet implemented")
    }
}