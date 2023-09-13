package com.chocolate.usecases.channel

import repositories.ChannelsRepository
import javax.inject.Inject

class SearchForChannelUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository
) {


}