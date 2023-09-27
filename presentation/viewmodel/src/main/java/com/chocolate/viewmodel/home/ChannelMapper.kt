package com.chocolate.viewmodel.home

import com.chocolate.entities.Channel

@JvmName("channelToChannelUiState")
fun Channel.toUiState(): ChannelUiState =
    ChannelUiState(
        id,
        name,
    )
@JvmName("channelsToChannelsUiState")
fun List<Channel>.toUiState(): List<ChannelUiState> = this.map { it.toUiState() }