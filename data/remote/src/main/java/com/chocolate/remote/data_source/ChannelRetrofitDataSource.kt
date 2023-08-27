package com.chocolate.remote.data_source

import com.chocolate.remote.api.ChannelsService
import com.chocolate.remote.wrapApiCall
import com.chocolate.repository.datastore.remote.ChannelRemoteDataSource
import com.chocolate.repository.model.dto.channels.response.AllStreamsDto
import com.chocolate.repository.model.dto.channels.response.AllSubscribersDto
import com.chocolate.repository.model.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.model.dto.channels.response.StreamsByIdDto
import com.chocolate.repository.model.dto.channels.response.StreamsIdDto
import com.chocolate.repository.model.dto.channels.response.SubscribeToStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscribedStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscriptionStatusDto
import com.chocolate.repository.model.dto.channels.response.TopicsInStreamDto
import com.chocolate.repository.model.dto.channels.response.UnsubscribeFromStreamDto
import javax.inject.Inject

class ChannelRetrofitDataSource @Inject constructor(
    private val channelsService: ChannelsService
) : ChannelRemoteDataSource {

    override suspend fun getSubscribedChannels(): SubscribedStreamDto {
        return wrapApiCall { channelsService.getSubscribedChannels() }
    }

    override suspend fun subscribeToChannels(
        channelName: String,
        description: String?,
        isPrivate: Boolean,
        usersId: String
    ): SubscribeToStreamDto {
        return wrapApiCall {
            channelsService.subscribeToChannels(
                channelsName = channelName,
                usersId = usersId,
                isPrivate = isPrivate
            )
        }
    }

    override suspend fun unsubscribeFromChannels(
        channelsName: List<String>
    ): UnsubscribeFromStreamDto {
        return wrapApiCall { channelsService.unsubscribeFromChannels(channelsName) }
    }

    override suspend fun getSubscriptionStatus(
        userId: Int, channelId: Int
    ): SubscriptionStatusDto {
        return wrapApiCall { channelsService.getSubscriptionStatus(userId, channelId) }
    }

    override suspend fun getSubscribersInChannel(channelId: Int): AllSubscribersDto {
        return wrapApiCall { channelsService.getSubscribersInChannel(channelId) }
    }

    override suspend fun getChannels(): AllStreamsDto {
        return wrapApiCall { channelsService.getChannels() }
    }

    override suspend fun getChannelById(channelId: Int): StreamsByIdDto {
        return wrapApiCall { channelsService.getChannelById(channelId) }
    }

    override suspend fun getChannelIdByName(channelName: String): StreamsIdDto {
        return wrapApiCall { channelsService.getChannelIdByName(channelName) }
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
    ): DefaultStreamDto {
        return wrapApiCall {
            channelsService.updateChannel(
                streamId,
                description,
                newName,
                isPrivate,
            )
        }
    }

    override suspend fun archiveChannel(channelId: Int): DefaultStreamDto {
        return wrapApiCall { channelsService.archiveChannel(channelId) }
    }

    override suspend fun getTopicsInChannel(channelId: Int): TopicsInStreamDto {
        return wrapApiCall { channelsService.getTopicsInChannel(channelId) }
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
    ): DefaultStreamDto {
        return wrapApiCall { channelsService.setTopicMuting(topic, status, streamId) }
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): DefaultStreamDto {
        return wrapApiCall {
            channelsService.updatePersonalPreferenceTopic(streamId, topic, visibilityPolicy)
        }
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): DefaultStreamDto {
        return wrapApiCall { channelsService.deleteTopic(channelId, topicName) }
    }

    override suspend fun addDefaultStream(channelId: Int): DefaultStreamDto {
        return wrapApiCall {
            channelsService.addDefaultChannel(channelId)
        }
    }

    override suspend fun deleteDefaultStream(channelId: Int): DefaultStreamDto {
        return wrapApiCall { channelsService.deleteDefaultChannel(channelId) }
    }
}