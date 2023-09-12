package com.chocolate.viewmodel.search

import com.chocolate.entities.channel.Channel

@JvmName("channelToSearchChannelUiState")

fun Channel.toUiState(): SearchChannelUiState =
    SearchChannelUiState(
        id,
        name,
        isPrivate=isPrivate
    )
@JvmName("channelsToSearchChannelsUiState")
fun List<Channel>.toUiState(): List<SearchChannelUiState> = this.map { it.toUiState() }