package com.chocolate.repository.repository

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.MutingStatus
import com.chocolate.entities.channel.Topic
import com.chocolate.repository.mappers.channel_mappers.toEntity
import com.chocolate.repository.mappers.channel_mappers.toSuccessOrFail
import com.chocolate.repository.service.remote.RemoteDataSource
import repositories.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(
    private val channelsRemoteDataSource: RemoteDataSource,
) : ChannelsRepository {

    override suspend fun getChannels(): List<Channel> {
        return channelsRemoteDataSource.getChannels().streams.toEntity()
    }

    override suspend fun getSubscribedChannels(): List<Channel> =
        channelsRemoteDataSource.getSubscribedChannels().toEntity { channelId ->
            getTopicsInChannel(channelId)
        }

    override suspend fun subscribeToChannel(channelName: String): Boolean {
        return channelsRemoteDataSource.subscribeToChannels(listOf(Pair("name", channelName)))
            .result?.equals("success") ?: false
    }

    override suspend fun unsubscribeFromChannel(
        channelName: String,
    ): Boolean {
        return channelsRemoteDataSource.unsubscribeFromChannels(listOf(channelName))
            .result?.equals("success") ?: false
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        channelId: Int
    ): Boolean {
        return channelsRemoteDataSource.getSubscriptionStatus(userId, channelId).isSubscribed
            ?: false
    }

    override suspend fun getSubscribersByChannelId(channelId: Int): List<Int> =
        channelsRemoteDataSource.getSubscribersInChannel(channelId).subscribers
            ?: emptyList()

    override suspend fun getChannelById(channelId: Int): Channel? {
        return channelsRemoteDataSource.getChannelById(channelId).streamDto?.toEntity()
    }

    override suspend fun getChannelIdByName(channel: String): Int {
        return channelsRemoteDataSource.getChannelIdByName(channel).streamId ?: 0
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
    ): Boolean {
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
        ).toSuccessOrFail()
    }

    override suspend fun archiveChannel(channelId: Int): Boolean {
        return channelsRemoteDataSource.archiveChannel(channelId).toSuccessOrFail()
    }

    override suspend fun getTopicsInChannel(channelId: Int): List<Topic> {
        return channelsRemoteDataSource.getTopicsInChannel(channelId).toEntity()
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: MutingStatus,
        streamId: Int?,
    ): Boolean {
        return channelsRemoteDataSource.setTopicMuting(topic, status.value, streamId)
            .toSuccessOrFail()
    }

    override suspend fun updatePersonalPreferenceTopic(
        channelId: Int,
        topic: String,
        visibilityPolicy: Int
    ): Boolean {
        return channelsRemoteDataSource.updatePersonalPreferenceTopic(
            channelId,
            topic,
            visibilityPolicy
        ).toSuccessOrFail()
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): Boolean {
        return channelsRemoteDataSource.deleteTopic(channelId, topicName)
            .toSuccessOrFail()
    }

    override suspend fun addDefaultChannel(channelId: Int): Boolean {
        return channelsRemoteDataSource.addDefaultStream(channelId).toSuccessOrFail()
    }

    override suspend fun deleteDefaultChannel(channelId: Int): Boolean {
        return channelsRemoteDataSource.deleteDefaultStream(channelId)
            .toSuccessOrFail()
    }
}