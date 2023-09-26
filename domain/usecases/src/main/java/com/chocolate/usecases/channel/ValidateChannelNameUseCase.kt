package com.chocolate.usecases.channel

import com.chocolate.entities.exceptions.InvalidChannelNameException
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import repositories.ChannelRepository
import javax.inject.Inject

class ValidateChannelNameUseCase @Inject constructor(
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase,
    private val repository: ChannelRepository,
) {
    suspend operator fun invoke(channelName: String): String {
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
        return repository.getChannelsInOrganizationByOrganizationName(currentOrganizationName)
            .map { channels -> channels.any { it.name == channelName } }.first()
    }

}