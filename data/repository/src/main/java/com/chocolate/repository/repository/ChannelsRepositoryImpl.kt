package com.chocolate.repository.repository

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.MutingStatus
import com.chocolate.entities.channel.Topic
import com.chocolate.repository.datastore.remote.ChannelDataSource
import com.chocolate.repository.mappers.channel_mappers.toEntity
import com.chocolate.repository.utils.SUCCESS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject
import repositories.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(
    private val realTimeDataSource: ChannelDataSource,
): ChannelsRepository {
    /*
    override suspend fun getChannels(): List<Channel> {
        return channelRemoteDataSource.getChannels().streams.toEntity()
    }

    override suspend fun getStreamChannels(): Flow<List<Channel>> {
        return realTimeDataSource.getChannels().map { channels ->
            channels.map {
                it.toChannel()
            }
        }
    }

    override suspend fun getSubscribedChannels(): List<Channel> =
        channelRemoteDataSource.getSubscribedChannels().toEntity { channelId ->
            getTopicsInChannel(channelId)
        }

    override suspend fun subscribeToChannel(
        channelName: String,
        usersId: List<Int>,
        description: String?,
        isPrivate: Boolean,
    ): Boolean {
        return channelRemoteDataSource.subscribeToChannels(
            createJsonArrayString(channelName = channelName, channelDescription = description),
            usersId = JSONArray(usersId).toString(),
            description = description,
            isPrivate = isPrivate
        ).also {
            if (usersId.isNotEmpty()) realTimeDataSource.createChannel(
                channelName,
                usersId,
                isPrivate,
                description
            )
        }.result?.equals(SUCCESS) ?: false
    }

    override suspend fun unsubscribeFromChannel(
        channelName: String,
    ): Boolean {
        return channelRemoteDataSource.unsubscribeFromChannels(listOf(channelName))
            .result?.equals(SUCCESS) ?: false
    }

    override suspend fun getSubscriptionStatus(
        userId: Int,
        channelId: Int,
    ): Boolean {
        return channelRemoteDataSource.getSubscriptionStatus(userId, channelId).isSubscribed
            ?: false
    }

    override suspend fun getSubscribersByChannelId(channelId: Int): List<Int> =
        channelRemoteDataSource.getSubscribersInChannel(channelId).subscribers
            ?: emptyList()

    override suspend fun getChannelById(channelId: Int): Channel? {
        return null
    }

    override suspend fun getChannelIdByName(channel: String): Int {
        return channelRemoteDataSource.getChannelIdByName(channel).streamId ?: -1
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
        canRemoveSubscribersGroupId: Int?,
    ): Boolean {
        return channelRemoteDataSource.updateStream(
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
        return channelRemoteDataSource.archiveChannel(channelId).toSuccessOrFail()
    }

    override suspend fun getTopicsInChannel(channelId: Int): List<Topic> {
        return channelRemoteDataSource.getTopicsInChannel(channelId).toEntity()
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: MutingStatus,
        streamId: Int?,
    ): Boolean {
        return channelRemoteDataSource.setTopicMuting(topic, status.value, streamId)
            .toSuccessOrFail()
    }

    override suspend fun updatePersonalPreferenceTopic(
        channelId: Int,
        topic: String,
        visibilityPolicy: Int,
    ): Boolean {
        return channelRemoteDataSource.updatePersonalPreferenceTopic(
            channelId,
            topic,
            visibilityPolicy
        ).toSuccessOrFail()
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): Boolean {
        return channelRemoteDataSource.deleteTopic(channelId, topicName)
            .toSuccessOrFail()
    }

    override suspend fun addDefaultChannel(channelId: Int): Boolean {
        return channelRemoteDataSource.addDefaultStream(channelId).toSuccessOrFail()
    }

    override suspend fun deleteDefaultChannel(channelId: Int): Boolean {
        return channelRemoteDataSource.deleteDefaultStream(channelId)
            .toSuccessOrFail()
    }

    private fun createJsonArrayString(
        channelName: String,
        channelDescription: String? = "",
    ): String {
        val jsonArray = JSONArray()
        val jsonObject = JSONObject()
        jsonObject.put("name", channelName)
        jsonObject.put("description", channelDescription)
        jsonArray.put(jsonObject)
        return jsonArray.toString()
    }*/
    override suspend fun getSubscribedChannels(): List<Channel> {
        TODO("Not yet implemented")
    }

    override suspend fun subscribeToChannel(
        channelName: String,
        usersId: List<Int>,
        description: String?,
        isPrivate: Boolean
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun unsubscribeFromChannel(channelName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getSubscriptionStatus(userId: Int, channelId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getSubscribersByChannelId(channelId: Int): List<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getChannels(): List<Channel> {
        TODO("Not yet implemented")
    }

    override suspend fun getChannelById(channelId: Int): Channel? {
        TODO("Not yet implemented")
    }

    override suspend fun getChannelIdByName(channel: String): Int {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override suspend fun archiveChannel(channelId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getTopicsInChannel(channelId: Int): List<Topic> {
        TODO("Not yet implemented")
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: MutingStatus,
        streamId: Int?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updatePersonalPreferenceTopic(
        channelId: Int,
        topic: String,
        visibilityPolicy: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addDefaultChannel(channelId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDefaultChannel(channelId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getStreamChannels(): Flow<List<Channel>> {
        TODO("Not yet implemented")
    }

}