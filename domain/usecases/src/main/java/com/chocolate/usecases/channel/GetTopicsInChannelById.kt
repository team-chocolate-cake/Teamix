package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Topic
import repositories.ChannelsRepository
import javax.inject.Inject

class GetTopicsInChannelById @Inject constructor(
    private val channelsRepository: ChannelsRepository
) {

    suspend operator fun invoke(channelId: Int): List<Topic> =
        channelsRepository.getTopicsInChannel(channelId)

}