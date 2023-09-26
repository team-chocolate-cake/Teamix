package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import com.chocolate.usecases.member.GetIdOfCurrentMemberUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import kotlinx.coroutines.flow.Flow
import repositories.ChannelRepository
import javax.inject.Inject

class GetChannelsForCurrentMemberUseCase @Inject constructor(
    private val channelRepository: ChannelRepository,
    private val getIdOfCurrentMemberUseCase: GetIdOfCurrentMemberUseCase,
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase
) {
    suspend operator fun invoke(): Flow<List<Channel>> {
        val currentMemberId = getIdOfCurrentMemberUseCase()
        val currentOrganizationName = manageOrganizationDetailsUseCase.getOrganizationName()
        return channelRepository.getChannelsForMemberByMemberIdInOrganization(
            currentMemberId,
            currentOrganizationName
        )
    }
}