package com.chocolate.repository.datasource.remote

import com.chocolate.repository.model.dto.channel.ChannelDto
import kotlinx.coroutines.flow.Flow

interface ChannelDataSource {
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