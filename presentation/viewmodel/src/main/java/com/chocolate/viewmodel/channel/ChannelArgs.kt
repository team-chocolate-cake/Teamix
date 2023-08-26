package com.chocolate.viewmodel.channel

import androidx.lifecycle.SavedStateHandle

class ChannelArgs(savedStateHandle: SavedStateHandle) {
    val channelId: Int = (checkNotNull(savedStateHandle[CHANNEL_ID]) as String).toInt()
    val channelName: String = checkNotNull(savedStateHandle[CHANNEL_NAME])

    companion object {
        const val CHANNEL_ID = "Channel_Id"
        const val CHANNEL_NAME = "Channel_Name"
    }
}