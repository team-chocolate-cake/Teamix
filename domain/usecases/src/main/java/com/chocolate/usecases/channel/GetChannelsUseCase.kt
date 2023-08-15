package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import repositories.ChannelsRepository
import javax.inject.Inject

class GetChannelsUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val getTopicsInChannelById: GetTopicsInChannelById
) {
    suspend operator fun invoke(): List<Channel> {
        return channelsRepository.getChannels().map{ channel ->
            Channel(
                channelId = channel.channelId,
                channelName = channel.channelName,
                description = channel.description,
                invitationONly = channel.invitationONly,
                topics = getTopicsInChannelById(channelId = channel.channelId)
            )
        }
    }
}