package com.chocolate.remote.channels.implementation.remote

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
) : ChannelsDataSource {
    override suspend fun getUserSubscriptions(
        includeSubscribers: Boolean
    ): Response<SubscribedStreamDto> {
        return channelsService.getUserSubscriptions(includeSubscribers)
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
        return channelsService.addSubscribesToStream(
            subscribeToStream,
            principals,
            authorizationErrorsFatal,
            announce,
            inviteOnly,
            isWebPublic,
            historyPublicToSubscribers,
            streamPostPolicy,
            messageRetentionDays,
            canRemoveSubscribersGroupId,
        )
    }


    override suspend fun deleteSubscriberFromStream(
        subscriptions: String,
        principals: List<String>?
    ): Response<UnsubscribeFromStreamDto> {
        return channelsService.deleteSubscriberFromStream(
            subscriptions,
            principals
        )
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int
    ): Response<SubscriptionStatusDto> {
        return channelsService.getSubscriptionStatus(userId, streamId)
    }

    override suspend fun getAllSubscribers(streamId: Int): Response<AllSubscribersDto> {
        return channelsService.getAllSubscriber(streamId)
    }

    override suspend fun updateSubscriptionSettings(subscriptionData: String): Response<SubscriptionSettingsDto> {
        return channelsService.updateSubscriptionSettings(subscriptionData)
    }

    override suspend fun getAllStreams(
        includePublic: Boolean,
        includeWebPublic: Boolean,
        includeSubscribed: Boolean,
        includeAllActive: Boolean,
        includeDefault: Boolean,
        includeOwnerSubscribed: Boolean
    ): Response<AllStreamsDto> {
        return channelsService.getAllStreams(
            includePublic,
            includeWebPublic,
            includeSubscribed,
            includeAllActive,
            includeDefault,
            includeOwnerSubscribed
        )
    }

    override suspend fun getStreamById(streamId: Int): Response<StreamsByIdDto> {
        return channelsService.getStreamById(streamId)
    }

    override suspend fun getStreamId(stream: String): Response<StreamsIdDto> {
        return channelsService.getStreamId(stream)
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
        return channelsService.updateStream(
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

    override suspend fun archiveStream(streamId: Int): Response<DefaultStreamDto> {
        return channelsService.archiveStream(streamId)
    }

    override suspend fun getTopicsInStream(streamId: Int): Response<TopicsInStreamDto> {
        return channelsService.getTopicsInStream(streamId)
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
        stream: String?
    ): Response<DefaultStreamDto> {
        return channelsService.setTopicMuting(topic, status, streamId, stream)
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): Response<DefaultStreamDto> {
        return channelsService.updatePersonalPreferenceTopic(streamId, topic, visibilityPolicy)
    }

    override suspend fun deleteTopic(streamId: Int, topicName: String): Response<DefaultStreamDto> {
        return channelsService.deleteTopic(streamId, topicName)
    }

    override suspend fun addDefaultStream(streamId: Int): Response<DefaultStreamDto> {
        return channelsService.addDefaultStream(streamId)
    }

    override suspend fun deleteDefaultStream(streamId: Int): Response<DefaultStreamDto> {
        return channelsService.deleteDefaultStream(streamId)
    }
}