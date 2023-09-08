package com.chocolate.viewmodel.topic

import androidx.lifecycle.SavedStateHandle
import com.chocolate.viewmodel.channel.ChannelArgs

class TopicArgs(savedStateHandle: SavedStateHandle) {
    val topicId: Int = (checkNotNull(savedStateHandle[TOPIC_ID]) as String).toInt()
    val topicName: String = checkNotNull(savedStateHandle[TOPIC_NAME])

    companion object {
        const val TOPIC_NAME = "Topic_Name"
        const val TOPIC_ID = "Topic_ID"

    }
}