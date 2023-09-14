package com.chocolate.viewmodel.topic

import androidx.lifecycle.SavedStateHandle

class TopicArgs(savedStateHandle: SavedStateHandle) {
    val channelId: String = (checkNotNull(savedStateHandle[CHANNEL_ID]) as String)

    companion object {
    const val CHANNEL_ID = "Channel_Id"
    }
}