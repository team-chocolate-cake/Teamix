package com.chocolate.usecases.channel

import repositories.ChannelsRepository
import repositories.UsersRepositories
import javax.inject.Inject

class IsUserSubscribedToChannelUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val usersRepositories: UsersRepositories
) {

    suspend operator fun invoke(channelId: Int): Boolean {
        return channelsRepository.getSubscriptionStatus(
            userId = usersRepositories.getOwnUser().userId,
            channelId = channelId
        )
    }

}