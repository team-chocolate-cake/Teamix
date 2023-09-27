package com.chocolate.usecases.channel

import com.chocolate.entities.entity.Channel
import com.chocolate.entities.util.InvalidChannelNameException
import com.chocolate.entities.util.getRandomId
import com.chocolate.usecases.member.GetIdOfCurrentMemberUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import repositories.ChannelRepository
import javax.inject.Inject

class ManageChannelUseCase @Inject constructor(
    private val channelRepository: ChannelRepository,
    private val getIdOfCurrentMemberUseCase: GetIdOfCurrentMemberUseCase,
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase
) {
    suspend fun createChannel(
        channelName: String,
        usersId: List<String>,
        description: String?,
    ): Boolean {
        val currentOrganizationName = manageOrganizationDetailsUseCase.getOrganizationName()
        val channel = Channel(
            id = getRandomId().toString(),
            name = channelName,
            membersId = usersId,
            description = description ?: "",
        )
        channelRepository.createChannelInOrganization(
            organizationName = currentOrganizationName,
            channel = channel
        )
        return true
    }

    suspend fun getChannelsForCurrentMember(): Flow<List<Channel>> {
        val currentMemberId = getIdOfCurrentMemberUseCase()
        val currentOrganizationName = manageOrganizationDetailsUseCase.getOrganizationName()
        return channelRepository.getChannelsForMemberByMemberIdInOrganization(
            currentMemberId,
            currentOrganizationName
        )
    }

    suspend fun validateChannelName(channelName: String): String {
        return if (channelName.isBlank() || channelName.length > 60 || isChannelAlreadyExists(
                channelName
            )
        ) {
            throw InvalidChannelNameException
        } else {
            channelName
        }
    }

    private suspend fun isChannelAlreadyExists(channelName: String): Boolean {
        val currentOrganizationName = manageOrganizationDetailsUseCase.getOrganizationName()
        return channelRepository.getChannelsInOrganizationByOrganizationName(currentOrganizationName)
            .map { channels -> channels.any { it.name == channelName } }.first()
    }
}