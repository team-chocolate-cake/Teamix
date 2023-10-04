package com.chocolate.usecases.repositories

import com.chocolate.entities.entity.Channel
import kotlinx.coroutines.flow.Flow

interface ChannelRepository {
    suspend fun createChannelInOrganization(
        channel: Channel,
        organizationName: String,
    )

    suspend fun getChannelsInOrganizationByOrganizationName(
        organizationName: String
    ): Flow<List<Channel>>

    suspend fun getChannelsForMemberByMemberIdInOrganization(
        currentMemberId: String,
        organizationName: String
    ): Flow<List<Channel>>
}