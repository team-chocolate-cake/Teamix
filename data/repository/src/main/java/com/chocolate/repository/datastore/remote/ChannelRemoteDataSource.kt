package com.chocolate.repository.datastore.remote

import com.chocolate.repository.model.dto.channels.ChannelDto
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
import kotlinx.coroutines.flow.Flow

interface ChannelRemoteDataSource {
    suspend fun createChannel(
        channel: ChannelDto,
        organizationName: String
    )

    suspend fun getChannelsForCurrentMember(organizationName: String,memberId:String):Flow<List<ChannelDto>>

    suspend fun getChannelsInOrganizationByOrganizationName(organizationName: String): Flow<List<ChannelDto>?>

//    suspend fun getSubscribedChannels(): SubscribedStreamDto
//
//    suspend fun subscribeToChannels(
//        channelName: String,
//        description: String?,
//        isPrivate: Boolean,
//        usersId: String
//    ): SubscribeToStreamDto
//
//    suspend fun unsubscribeFromChannels(channelsName: List<String>): UnsubscribeFromStreamDto
//
//    suspend fun getSubscriptionStatus(userId: Int, channelId: Int): SubscriptionStatusDto
//
//    suspend fun getSubscribersInChannel(channelId: Int): AllSubscribersDto
//
//    suspend fun getChannels(): AllStreamsDto
//
//    suspend fun getChannelById(
//        channelId: Int
//    ): StreamsByIdDto
//
//    suspend fun getChannelIdByName(
//        channelName: String
//    ): StreamsIdDto
//
//    suspend fun updateStream(
//        streamId: Int,
//        description: String? = null,
//        newName: String? = null,
//        isPrivate: Boolean? = null,
//        isWebPublic: Boolean? = null,
//        historyPublicToSubscribers: Boolean? = null,
//        streamPostPolicy: Int? = null,
//        messageRetentionDays: String? = null,
//        canRemoveSubscribersGroupId: Int? = null,
//    ): DefaultStreamDto
//
//    suspend fun archiveChannel(
//        channelId: Int
//    ): DefaultStreamDto
//
//    suspend fun getTopicsInChannel(
//        channelId: Int
//    ): TopicsInStreamDto
//
//    suspend fun setTopicMuting(
//        topic: String,
//        status: String,
//        streamId: Int? = null,
//    ): DefaultStreamDto
//
//    suspend fun updatePersonalPreferenceTopic(
//        streamId: Int,
//        topic: String,
//        visibilityPolicy: Int,
//    ): DefaultStreamDto
//
//    suspend fun deleteTopic(
//        channelId: Int,
//        topicName: String
//    ): DefaultStreamDto
//
//    suspend fun addDefaultStream(
//        channelId: Int,
//    ): DefaultStreamDto
//
//    suspend fun deleteDefaultStream(
//        channelId: Int
//    ): DefaultStreamDto
}