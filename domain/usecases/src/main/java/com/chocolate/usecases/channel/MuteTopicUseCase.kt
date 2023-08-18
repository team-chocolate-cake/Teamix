package com.chocolate.usecases.channel

import com.chocolate.entities.channel.MutingStatus
import repositories.ChannelsRepository
import javax.inject.Inject

class MuteTopicUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository
) {
    suspend operator fun invoke(topic: String, status: MutingStatus, streamId: Int) =
        channelsRepository.setTopicMuting(topic, status, streamId)
}