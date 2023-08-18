package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import repositories.ChannelsRepository
import javax.inject.Inject

class GetChannelsUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository,
) {
    suspend operator fun invoke(): List<Channel> {
        return channelsRepository.getChannels()
    }
}