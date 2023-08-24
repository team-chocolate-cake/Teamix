package com.chocolate.usecases.channel

import repositories.ChannelsRepository
import javax.inject.Inject

class AddUsersInChannelByChannelNameAndUsersIdUseCase @Inject constructor(
    private val repository: ChannelsRepository
) {

    suspend operator fun invoke(channelName: String, usersId: List<Int>):Boolean {
        return repository.subscribeToChannel(channelName, usersId)
    }

}