package com.chocolate.viewmodel.createchannel

import androidx.lifecycle.SavedStateHandle

class CreateChannelArgs(savedStateHandle: SavedStateHandle) {
    val channelName: String = checkNotNull(savedStateHandle[CHANNEL_NAME])
    val description: String = checkNotNull(savedStateHandle[DESCRIPTION])

    companion object {
        const val CHANNEL_NAME = "channelName"
        const val DESCRIPTION = "description"
        const val IS_PRIVATE = "isPrivate"
    }
}