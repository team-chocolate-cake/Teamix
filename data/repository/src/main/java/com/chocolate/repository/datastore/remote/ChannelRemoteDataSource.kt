package com.chocolate.repository.datastore.remote

import com.chocolate.repository.model.dto.channels.ChannelDto
import kotlinx.coroutines.flow.Flow

interface ChannelRemoteDataSource {
    suspend fun createChannel(channel: ChannelDto, organizationName: String)

    suspend fun getChannelsForCurrentMember(
        organizationName: String,
        memberId: String
    ): Flow<List<ChannelDto>>

    suspend fun getChannelsInOrganizationByOrganizationName(organizationName: String): Flow<List<ChannelDto>?>

    suspend fun getChannelInOrganizationByChannelName(
        organizationName: String,
        channelName: String
    ): ChannelDto?
}