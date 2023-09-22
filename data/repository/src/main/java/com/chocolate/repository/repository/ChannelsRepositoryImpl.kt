package com.chocolate.repository.repository

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.EmptyMemberIdException
import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.uills.getRandomId
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.ChannelRemoteDataSource
import com.chocolate.repository.mappers.channel_mappers.toChannel
import com.chocolate.repository.model.dto.channels.ChannelDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(
    private val channelRemoteDataSource: ChannelRemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource,
) : ChannelsRepository {

    private suspend fun getCurrentOrganizationName(): String {
        return preferencesDataSource.getCurrentOrganizationName()
            ?: throw EmptyOrganizationNameException
    }

    override suspend fun getStreamChannels(): Flow<List<Channel>> {
        return channelRemoteDataSource.getChannelsInOrganizationByOrganizationName(
            getCurrentOrganizationName()
        ).map { channels -> channels!!.map { it.toChannel() } }
    }

    override suspend fun getChannelsForCurrentMember(
    ): Flow<List<Channel>> {
        val currentMemberId =
            preferencesDataSource.getIdOfCurrentMember() ?: throw EmptyMemberIdException
        return channelRemoteDataSource.getChannelsForCurrentMember(
            getCurrentOrganizationName(),
            currentMemberId
        ).map { channels -> channels.map { it.toChannel() } }
    }


    override suspend fun subscribeToChannel(
        channelName: String,
        usersId: List<String>,
        description: String?,
        isPrivate: Boolean,
    ): Boolean {
        if (usersId.isNotEmpty()) channelRemoteDataSource.createChannel(
            ChannelDto(
                id = getRandomId().toString(),
                name = channelName,
                membersId = usersId,
                description = description,
                isPrivate = isPrivate
            ), getCurrentOrganizationName()
        )
        return true
    }

    override suspend fun getChannelsInCurrentOrganization(): Flow<List<Channel>> {
        return channelRemoteDataSource.getChannelsInOrganizationByOrganizationName(
            getCurrentOrganizationName()
        ).map { channelsDto ->
            channelsDto?.let {
                it.map { channelDto -> channelDto.toChannel() }
            } ?: emptyList()
        }
    }

}