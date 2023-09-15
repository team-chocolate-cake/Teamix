package com.chocolate.viewmodel.createTopic

import androidx.lifecycle.SavedStateHandle
import javax.inject.Inject

class CreateTopicArgs @Inject constructor(savedStateHandle: SavedStateHandle) {

    val channelId: String = checkNotNull(savedStateHandle[CHANNEL_Id])

    companion object {
        const val CHANNEL_Id = "channel_Id"
    }
}
