package com.chocolate.repository.repository

import com.chocolate.entities.channel_models.ChannelDetails
import com.chocolate.entities.channel_models.ChannelId
import com.chocolate.entities.channel_models.ChannelSubscribers
import com.chocolate.entities.channel_models.StreamItem
import com.chocolate.entities.channel_models.SubscribeToStream
import com.chocolate.entities.channel_models.SubscriptionSettingsUpdate
import com.chocolate.entities.channel_models.SubscriptionStatus
import com.chocolate.entities.channel_models.DefaultChannelModel
import com.chocolate.entities.channel_models.Topics
import com.chocolate.repository.mappers.channel_mappers.toChannelDefault
import com.chocolate.repository.mappers.channel_mappers.toChannelDetails
import com.chocolate.repository.mappers.channel_mappers.toChannelId
import com.chocolate.repository.mappers.channel_mappers.toChannelSubscribers
import com.chocolate.repository.mappers.channel_mappers.toStreamInfo
import com.chocolate.repository.mappers.channel_mappers.toStreamItem
import com.chocolate.repository.mappers.channel_mappers.toSubscribeToStream
import com.chocolate.repository.mappers.channel_mappers.toSubscriptionSettingsUpdate
import com.chocolate.repository.mappers.channel_mappers.toSubscriptionStatus
import com.chocolate.repository.mappers.channel_mappers.toTopics
import com.chocolate.repository.mappers.channel_mappers.toUnsubscribeFromStream
import com.chocolate.repository.service.remote.RemoteDataSource
import repositories.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(
    private val channelsRemoteDataSource: RemoteDataSource,
) : ChannelsRepository{
    override suspend fun getUserSubscriptions(): List<StreamItem?>? {
        return channelsRemoteDataSource.getUserSubscriptions()
            .subscriptions.let { items -> items?.map { it.toStreamInfo() } }
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
        return channelsRemoteDataSource.addSubscribesToStream(
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
            ).toSubscribeToStream()
    }

    override suspend fun deleteSubscriber(
        subscriptions: String,
        principals: List<String>?
    ): DefaultChannelModel {
        return channelsRemoteDataSource.deleteSubscriberFromStream(
            subscriptions,
            principals
        ).toUnsubscribeFromStream()
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int
    ): SubscriptionStatus {
        return channelsRemoteDataSource.getSubscriptionStatus(userId, streamId).toSubscriptionStatus()
    }

    override suspend fun getAllSubscriber(streamId: Int): ChannelSubscribers {
        return channelsRemoteDataSource.getAllSubscribers(streamId).toChannelSubscribers()
    }

    override suspend fun updateSubscriptionSettings(subscriptionData: String): SubscriptionSettingsUpdate {
        return channelsRemoteDataSource.updateSubscriptionSettings(subscriptionData).toSubscriptionSettingsUpdate()
    }

    override suspend fun getAllChannels(
        includePublic: Boolean,
        includeWebPublic: Boolean,
        includeSubscribed: Boolean,
        includeAllActive: Boolean,
        includeDefault: Boolean,
        includeOwnerSubscribed: Boolean
    ): List<StreamItem> {
        return channelsRemoteDataSource.getAllStreams(
            includePublic,
            includeWebPublic,
            includeSubscribed,
            includeAllActive,
            includeDefault,
            includeOwnerSubscribed
        ).streams?.map { it.toStreamItem() } ?: emptyList()
    }

    override suspend fun getChannelById(streamId: Int): ChannelDetails {
        return channelsRemoteDataSource.getStreamById(streamId).streamDto?.toChannelDetails()
            ?: throw Exception("Channel details not found")
    }

    override suspend fun getChannelId(channel: String): ChannelId {
        return channelsRemoteDataSource.getStreamId(channel).toChannelId()
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
        return channelsRemoteDataSource.updateStream(
            streamId,
            description,
            newName,
            isPrivate,
            isWebPublic,
            historyPublicToSubscribers,
            streamPostPolicy,
            messageRetentionDays,
            canRemoveSubscribersGroupId
        ).toChannelDefault()
    }

    override suspend fun archiveChannel(channelId: Int): DefaultChannelModel {
        return channelsRemoteDataSource.archiveStream(channelId).toChannelDefault()
    }

    override suspend fun getTopicsInChannel(channelId: Int): Topics {
        return channelsRemoteDataSource.getTopicsInStream(channelId).toTopics()
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
        stream: String?
    ): DefaultChannelModel {
        return channelsRemoteDataSource.setTopicMuting(topic, status, streamId, stream).toChannelDefault()
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): DefaultChannelModel {
        return channelsRemoteDataSource.updatePersonalPreferenceTopic(streamId, topic, visibilityPolicy).toChannelDefault()
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): DefaultChannelModel {
        return channelsRemoteDataSource.deleteTopic(channelId, topicName).toChannelDefault()
    }

    override suspend fun addDefaultChannel(channelId: Int): DefaultChannelModel {
        return channelsRemoteDataSource.addDefaultStream(channelId).toChannelDefault()
    }

    override suspend fun deleteDefaultChannel(channelId: Int): DefaultChannelModel {
        return channelsRemoteDataSource.deleteDefaultStream(channelId).toChannelDefault()
    }
}