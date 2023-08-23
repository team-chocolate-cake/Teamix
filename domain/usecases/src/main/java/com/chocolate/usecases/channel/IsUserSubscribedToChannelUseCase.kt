package com.chocolate.usecases.channel

import repositories.ChannelsRepository
import repositories.UsersRepository
import javax.inject.Inject

class IsUserSubscribedToChannelUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val usersRepositories: UsersRepository
) {

    suspend operator fun invoke(channelId: Int): Boolean {
        return channelsRepository.getSubscriptionStatus(
            userId = usersRepositories.getRemoteCurrentUser().id,
            channelId = channelId
        )
    }

}