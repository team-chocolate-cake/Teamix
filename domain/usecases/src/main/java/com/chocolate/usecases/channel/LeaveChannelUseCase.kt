package com.chocolate.usecases.channel

import repositories.ChannelsRepository
import javax.inject.Inject

class LeaveChannelUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository
) {
    suspend operator fun invoke(channelName: String) =
        channelsRepository.unsubscribeFromChannel(channelName)
}