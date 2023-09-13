package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import kotlinx.coroutines.flow.Flow
import repositories.ChannelsRepository
import repositories.MemberRepository
import javax.inject.Inject

class ManageChannelsUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val usersRepositories: MemberRepository
) {

    suspend fun getChannelsInCurrentOrganization()
    =channelsRepository.getChannelsInCurrentOrganization()

    suspend fun getChannelsForCurrentMember()
    =channelsRepository.getChannelsForCurrentMember()

}