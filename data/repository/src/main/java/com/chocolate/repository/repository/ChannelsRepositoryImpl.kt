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
) : ChannelsRepository, BaseRepository() {
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
        return wrapCall {
            channelsRemoteDataSource.addSubscribesToStream(
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
        return wrapCall {
            channelsRemoteDataSource.deleteSubscriberFromStream(
                subscriptions,
                principals
            )
        }.toUnsubscribeFromStream()
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        streamId: Int
    ): SubscriptionStatus {
        return wrapCall { channelsRemoteDataSource.getSubscriptionStatus(userId, streamId) }
            .toSubscriptionStatus()
    }

    override suspend fun getAllSubscriber(streamId: Int): ChannelSubscribers {
        return wrapCall { channelsRemoteDataSource.getAllSubscribers(streamId) }.toChannelSubscribers()
    }

    override suspend fun updateSubscriptionSettings(subscriptionData: String): SubscriptionSettingsUpdate {
        return wrapCall { channelsRemoteDataSource.updateSubscriptionSettings(subscriptionData) }.toSubscriptionSettingsUpdate()
    }

    override suspend fun getAllChannels(
        includePublic: Boolean,
        includeWebPublic: Boolean,
        includeSubscribed: Boolean,
        includeAllActive: Boolean,
        includeDefault: Boolean,
        includeOwnerSubscribed: Boolean
    ): List<StreamItem> {
        return wrapCall {
            channelsRemoteDataSource.getAllStreams(
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
        return wrapCall { channelsRemoteDataSource.getStreamById(streamId) }.streamDto?.toChannelDetails()!!
    }

    override suspend fun getChannelId(channel: String): ChannelId {
        return wrapCall { channelsRemoteDataSource.getStreamId(channel) }.toChannelId()
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
        return wrapCall {
            channelsRemoteDataSource.updateStream(
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
        return wrapCall { channelsRemoteDataSource.archiveStream(channelId) }.toChannelDefault()
    }

    override suspend fun getTopicsInChannel(channelId: Int): Topics {
        return wrapCall { channelsRemoteDataSource.getTopicsInStream(channelId) }.toTopics()
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
        stream: String?
    ): DefaultChannelModel {
        return wrapCall { channelsRemoteDataSource.setTopicMuting(topic, status, streamId, stream) }
            .toChannelDefault()
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): DefaultChannelModel {
        return wrapCall {
            channelsRemoteDataSource.updatePersonalPreferenceTopic(streamId, topic, visibilityPolicy)
        }.toChannelDefault()
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): DefaultChannelModel {
        return wrapCall {  channelsRemoteDataSource.deleteTopic(channelId, topicName)}.toChannelDefault()
    }

    override suspend fun addDefaultChannel(channelId: Int): DefaultChannelModel {
        return wrapCall {  channelsRemoteDataSource.addDefaultStream(channelId)}.toChannelDefault()
    }

    override suspend fun deleteDefaultChannel(channelId: Int): DefaultChannelModel {
        return wrapCall {   channelsRemoteDataSource.deleteDefaultStream(channelId)}.toChannelDefault()
    }
}