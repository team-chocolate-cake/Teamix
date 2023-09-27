package com.chocolate.repository.repository

import com.chocolate.entities.entity.Channel
import com.chocolate.repository.datasource.remote.ChannelDataSource
import com.chocolate.repository.mappers.channel.toEntity
import com.chocolate.repository.mappers.channel.toRemote
import kotlinx.coroutines.flow.Flow
import repositories.ChannelRepository
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val channelDataSource: ChannelDataSource,
) : ChannelRepository {

    override suspend fun getChannelsForMemberByMemberIdInOrganization(
        currentMemberId: String,
        organizationName: String
    ): Flow<List<Channel>> {
        return channelDataSource.getChannelsForMemberByMemberIdAndOrganizationName(
            organizationName,
            currentMemberId
        ).toEntity()
    }

    override suspend fun createChannelInOrganization(
        channel: Channel,
        organizationName: String,
    ) {
        channelDataSource.createChannel(channel.toRemote(), organizationName)
    }

    override suspend fun getChannelsInOrganizationByOrganizationName(
        organizationName: String
    ): Flow<List<Channel>> {
        return channelDataSource.getChannelsInOrganizationByOrganizationName(organizationName)
            .toEntity()
    }
}