package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.uills.getRandomId
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import repositories.ChannelRepository
import javax.inject.Inject

class CreateChannelUseCase @Inject constructor(
    private val repository: ChannelRepository,
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase,
) {
    suspend operator fun invoke(
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
        repository.createChannelInOrganization(
            organizationName = currentOrganizationName,
            channel = channel
        )
        return true
    }
}