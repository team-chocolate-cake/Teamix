package com.chocolate.viewmodel.home

import com.chocolate.entities.channel.Channel

@JvmName("channelToChannelUiState")
fun Channel.toUiState(): ChannelUiState =
    ChannelUiState(
        id,
        name,
        isPrivateChannel=isPrivate
    )
@JvmName("channelsToChannelsUiState")
fun List<Channel>.toUiState(): List<ChannelUiState> = this.map { it.toUiState() }