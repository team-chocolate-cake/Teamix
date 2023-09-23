package com.chocolate.usecases.channel

import repositories.ChannelRepository
import repositories.MemberRepository
import javax.inject.Inject

class ManageChannelsUseCase @Inject constructor(
    private val channelRepository: ChannelRepository,
    private val usersRepositories: MemberRepository
) {

    suspend fun getChannelsInCurrentOrganization() =
        channelRepository.getChannelsInCurrentOrganization()

    suspend fun getChannelsForCurrentMember() = channelRepository.getChannelsForCurrentMember()

}