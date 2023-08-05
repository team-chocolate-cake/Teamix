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
    ): SubscribeToStream? {
        return channelsDataSource.addSubscribesToStream(
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
        ).body()?.toSubscribeToStream()
    }


    override suspend fun deleteSubscriber(
        subscriptions: String,
        principals: List<String>?
    ): DefaultChannelModel {
        return channelsDataSource.deleteSubscriberFromStream(subscriptions, principals).body()
            ?.toUnsubscribeFromStream()!!
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int
    ): SubscriptionStatus {
        return channelsDataSource.getSubscriptionStatus(userId, streamId).body()
            ?.toSubscriptionStatus()!!
    }

    override suspend fun getAllSubscriber(streamId: Int): ChannelSubscribers {
        return channelsDataSource.getAllSubscribers(streamId).body()?.toChannelSubscribers()!!
    }

    override suspend fun updateSubscriptionSettings(subscriptionData: String): SubscriptionSettingsUpdate? {
        return channelsDataSource.updateSubscriptionSettings(subscriptionData).body()
            ?.toSubscriptionSettingsUpdate()
    }

    override suspend fun getAllChannels(
        includePublic: Boolean,
        includeWebPublic: Boolean,
        includeSubscribed: Boolean,
        includeAllActive: Boolean,
        includeDefault: Boolean,
        includeOwnerSubscribed: Boolean
    ): List<StreamItem>? {
        return channelsDataSource.getAllStreams(
            includePublic,
            includeWebPublic,
            includeSubscribed,
            includeAllActive,
            includeDefault,
            includeOwnerSubscribed
        ).body()?.streams?.let { streamsItems -> streamsItems.map { it.toStreamItem() } }
    }

    override suspend fun getChannelById(streamId: Int): ChannelDetails? {
        return channelsDataSource.getStreamById(streamId).body()?.stream?.toChannelDetails()
    }

    override suspend fun getChannelId(channel: String): ChannelId? {
        return channelsDataSource.getStreamId(channel).body()?.toChannelId()
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
    ): DefaultChannelModel? {
        return channelsDataSource.updateStream(
            streamId,
            description,
            newName,
            isPrivate,
            isWebPublic,
            historyPublicToSubscribers,
            streamPostPolicy,
            messageRetentionDays,
            canRemoveSubscribersGroupId
        ).body()?.toChannelDefault()
    }

    override suspend fun archiveChannel(channelId: Int): DefaultChannelModel? {
        return channelsDataSource.archiveStream(channelId).body()?.toChannelDefault()
    }

    override suspend fun getTopicsInChannel(channelId: Int): Topics? {
        return channelsDataSource.getTopicsInStream(channelId).body()?.toTopics()
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
        stream: String?
    ): DefaultChannelModel? {
        return channelsDataSource.setTopicMuting(topic, status, streamId, stream).body()
            ?.toChannelDefault()
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): DefaultChannelModel? {
        return channelsDataSource.updatePersonalPreferenceTopic(streamId, topic, visibilityPolicy)
            .body()?.toChannelDefault()
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): DefaultChannelModel? {
        return channelsDataSource.deleteTopic(channelId, topicName).body()?.toChannelDefault()
    }

    override suspend fun addDefaultChannel(channelId: Int): DefaultChannelModel? {
        return channelsDataSource.addDefaultStream(channelId).body()?.toChannelDefault()
    }

    override suspend fun deleteDefaultChannel(channelId: Int): DefaultChannelModel? {
        return channelsDataSource.deleteDefaultStream(channelId).body()?.toChannelDefault()
    }
}