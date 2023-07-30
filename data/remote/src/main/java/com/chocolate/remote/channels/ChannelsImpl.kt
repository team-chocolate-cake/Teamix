package com.chocolate.remote.channels

import com.chocolate.remote.channels.service.ChannelsService
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
import com.chocolate.repository.service.ChannelsDataSource
import retrofit2.Response
import javax.inject.Inject

class ChannelsImpl @Inject constructor(
    private val channelsService: ChannelsService
): ChannelsDataSource {
    override suspend fun getSubscribedStreams(includeSubscribers: Boolean): Response<SubscribedStreamDto> {
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
    ): Response<SubscribeToStreamDto> {
        TODO("Not yet implemented")
    }


    override suspend fun deleteSubscriberFromStream(
        subscriptions: String,
        principals: List<String>?
    ): Response<UnsubscribeFromStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int
    ): Response<SubscriptionStatusDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSubscribers(streamId: Int): Response<AllSubscribersDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSubscriptionSettings(subscriptionData: String): Response<SubscriptionSettingsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllStreams(
        includePublic: Boolean,
        includeWebPublic: Boolean,
        includeSubscribed: Boolean,
        includeAllActive: Boolean,
        includeDefault: Boolean,
        includeOwnerSubscribed: Boolean
    ): Response<AllStreamsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getStreamById(streamId: Int): Response<StreamsByIdDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getStreamId(stream: String): Response<StreamsIdDto> {
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
    ): Response<DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun archiveStream(streamId: Int): Response<DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopicsInStream(streamId: Int): Response<TopicsInStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
        stream: String?
    ): Response<DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): Response<DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTopic(streamId: Int, topicName: String): Response<DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addDefaultStream(streamId: Int): Response<DefaultStreamDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDefaultStream(streamId: Int): Response<DefaultStreamDto> {
        TODO("Not yet implemented")
    }
}