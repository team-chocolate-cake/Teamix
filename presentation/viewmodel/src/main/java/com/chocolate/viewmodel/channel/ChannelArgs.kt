package com.chocolate.viewmodel.channel

import androidx.lifecycle.SavedStateHandle

class ChannelArgs(savedStateHandle: SavedStateHandle) {
    val channelId: Int = (checkNotNull(savedStateHandle[CHANNEL_ID]) as String).toInt()

    companion object {
        const val CHANNEL_ID = "Channel_Id"
    }
}