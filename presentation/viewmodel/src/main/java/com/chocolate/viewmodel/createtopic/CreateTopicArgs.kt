package com.chocolate.viewmodel.createtopic

import androidx.lifecycle.SavedStateHandle
import javax.inject.Inject

class CreateTopicArgs @Inject constructor(savedStateHandle: SavedStateHandle) {
    val channelName: String = checkNotNull(savedStateHandle[CHANNEL_NAME])

    companion object {
        const val CHANNEL_NAME = "channel_name"
    }
}