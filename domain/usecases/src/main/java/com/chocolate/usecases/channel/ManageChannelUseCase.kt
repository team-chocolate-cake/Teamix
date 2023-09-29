package com.chocolate.usecases.channel

import com.chocolate.entities.entity.Channel
import com.chocolate.entities.util.ChannelAlreadyExistException
import com.chocolate.entities.util.InvalidChannelNameException
import com.chocolate.entities.util.getRandomId
import com.chocolate.usecases.member.GetIdOfCurrentMemberUseCase
import com.chocolate.usecases.organization.ManageOrganizationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import repositories.ChannelRepository
import javax.inject.Inject

class ManageChannelUseCase @Inject constructor(
    private val channelRepository: ChannelRepository,
    private val getIdOfCurrentMemberUseCase: GetIdOfCurrentMemberUseCase,
    private val manageOrganizationUseCase: ManageOrganizationUseCase
) {
    suspend fun createChannel(
        channelName: String,
        usersId: List<String>,
        description: String?,
    ): Boolean {
        val currentOrganizationName = manageOrganizationUseCase.getOrganizationName()
        val channel = Channel(
            id = getRandomId().toString(),
            name = channelName,
            membersId = usersId,
            description = description ?: "Welcome to $channelName.",
        )
        channelRepository.createChannelInOrganization(
            organizationName = currentOrganizationName,
            channel = channel
        )
        return true
    }

    suspend fun getChannelsForCurrentMember(): Flow<List<Channel>> {
        val currentMemberId = getIdOfCurrentMemberUseCase()
        val currentOrganizationName = manageOrganizationUseCase.getOrganizationName()
        return channelRepository.getChannelsForMemberByMemberIdInOrganization(
            currentMemberId,
            currentOrganizationName
        )
    }

    suspend fun validateChannelName(channelName: String): String {
        return if (channelName.isBlank() || channelName.length > 60)
            throw InvalidChannelNameException
        else if (isChannelAlreadyExists(channelName))
            throw ChannelAlreadyExistException
        else {
            channelName
        }
    }

    private suspend fun isChannelAlreadyExists(channelName: String): Boolean {
        val currentOrganizationName = manageOrganizationUseCase.getOrganizationName()
        return channelRepository.getChannelsInOrganizationByOrganizationName(currentOrganizationName)
            .map { channels -> channels.any { it.name == channelName } }.first()
    }
}