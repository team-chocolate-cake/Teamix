package com.chocolate.repository.implementation.channels

import com.chocolate.entities.ChannelModels.ChannelDetails
import com.chocolate.entities.ChannelModels.ChannelId
import com.chocolate.entities.ChannelModels.ChannelSubscribers
import com.chocolate.entities.ChannelModels.StreamItem
import com.chocolate.entities.ChannelModels.SubscribeToStream
import com.chocolate.entities.ChannelModels.SubscriptionSettingsUpdate
import com.chocolate.entities.ChannelModels.SubscriptionStatus
import com.chocolate.entities.ChannelModels.DefaultChannelModel
import com.chocolate.entities.ChannelModels.Topics
import com.chocolate.repository.Mappers.ChannelMappers.toChannelDefault
import com.chocolate.repository.Mappers.ChannelMappers.toChannelDetails
import com.chocolate.repository.Mappers.ChannelMappers.toChannelId
import com.chocolate.repository.Mappers.ChannelMappers.toChannelSubscribers
import com.chocolate.repository.Mappers.ChannelMappers.toStreamInfo
import com.chocolate.repository.Mappers.ChannelMappers.toStreamItem
import com.chocolate.repository.Mappers.ChannelMappers.toSubscribeToStream
import com.chocolate.repository.Mappers.ChannelMappers.toSubscriptionSettingsUpdate
import com.chocolate.repository.Mappers.ChannelMappers.toSubscriptionStatus
import com.chocolate.repository.Mappers.ChannelMappers.toTopics
import com.chocolate.repository.Mappers.ChannelMappers.toUnsubscribeFromStream
import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.ChannelsDataSource
import repositories.channels.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(
    private val channelsDataSource: ChannelsDataSource,
) : ChannelsRepository, BaseRepository() {
    override suspend fun getUserSubscriptions(): List<StreamItem?>? {
        return channelsDataSource.getUserSubscriptions()
            .body()?.subscriptions.let { items -> items?.map { it?.toStreamInfo() } }
    }

    override suspend fun addSubscribes(
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
    ): SubscribeToStream {
        return wrapApiCall {
            channelsDataSource.addSubscribesToStream(
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
        }.toSubscribeToStream()
    }


    override suspend fun deleteSubscriber(
        subscriptions: String,
        principals: List<String>?
    ): DefaultChannelModel {
        return wrapApiCall {
            channelsDataSource.deleteSubscriberFromStream(
                subscriptions,
                principals
            )
        }.toUnsubscribeFromStream()
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int
    ): SubscriptionStatus {
        return wrapApiCall { channelsDataSource.getSubscriptionStatus(userId, streamId) }
            .toSubscriptionStatus()
    }

    override suspend fun getAllSubscriber(streamId: Int): ChannelSubscribers {
        return wrapApiCall { channelsDataSource.getAllSubscribers(streamId) }.toChannelSubscribers()
    }

    override suspend fun updateSubscriptionSettings(subscriptionData: String): SubscriptionSettingsUpdate {
        return wrapApiCall { channelsDataSource.updateSubscriptionSettings(subscriptionData) }.toSubscriptionSettingsUpdate()
    }

    override suspend fun getAllChannels(
        includePublic: Boolean,
        includeWebPublic: Boolean,
        includeSubscribed: Boolean,
        includeAllActive: Boolean,
        includeDefault: Boolean,
        includeOwnerSubscribed: Boolean
    ): List<StreamItem> {
        return wrapApiCall {
            channelsDataSource.getAllStreams(
                includePublic,
                includeWebPublic,
                includeSubscribed,
                includeAllActive,
                includeDefault,
                includeOwnerSubscribed
            )
        }.streams?.let { streamsItems -> streamsItems.map { it.toStreamItem() } }!!
    }

    override suspend fun getChannelById(streamId: Int): ChannelDetails {
        return wrapApiCall { channelsDataSource.getStreamById(streamId) }.stream?.toChannelDetails()!!
    }

    override suspend fun getChannelId(channel: String): ChannelId {
        return wrapApiCall { channelsDataSource.getStreamId(channel) }.toChannelId()
    }

    override suspend fun updateChannel(
        streamId: Int,
        description: String?,
        newName: String?,
        isPrivate: Boolean?,
        isWebPublic: Boolean?,
        historyPublicToSubscribers: Boolean?,
        streamPostPolicy: Int?,
        messageRetentionDays: String?,
        canRemoveSubscribersGroupId: Int?
    ): DefaultChannelModel {
        return wrapApiCall {
            channelsDataSource.updateStream(
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
        }.toChannelDefault()
    }

    override suspend fun archiveChannel(channelId: Int): DefaultChannelModel {
        return wrapApiCall { channelsDataSource.archiveStream(channelId) }.toChannelDefault()
    }

    override suspend fun getTopicsInChannel(channelId: Int): Topics {
        return wrapApiCall { channelsDataSource.getTopicsInStream(channelId) }.toTopics()
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
        stream: String?
    ): DefaultChannelModel {
        return wrapApiCall { channelsDataSource.setTopicMuting(topic, status, streamId, stream) }
            .toChannelDefault()
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): DefaultChannelModel {
        return wrapApiCall {
            channelsDataSource.updatePersonalPreferenceTopic(streamId, topic, visibilityPolicy)
        }.toChannelDefault()
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): DefaultChannelModel {
        return wrapApiCall {  channelsDataSource.deleteTopic(channelId, topicName)}.toChannelDefault()
    }

    override suspend fun addDefaultChannel(channelId: Int): DefaultChannelModel {
        return wrapApiCall {  channelsDataSource.addDefaultStream(channelId)}.toChannelDefault()
    }

    override suspend fun deleteDefaultChannel(channelId: Int): DefaultChannelModel {
        return wrapApiCall {   channelsDataSource.deleteDefaultStream(channelId)}.toChannelDefault()
    }
}