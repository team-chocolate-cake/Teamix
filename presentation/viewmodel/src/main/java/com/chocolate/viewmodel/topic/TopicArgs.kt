package com.chocolate.viewmodel.topic

import androidx.lifecycle.SavedStateHandle

class TopicArgs(savedStateHandle: SavedStateHandle) {
    val channelId: Int = (checkNotNull(savedStateHandle[CHANNEL_ID]) as String).toInt()
    val topicId: String = (checkNotNull(savedStateHandle[TOPIC_ID]) as String)
    val topicName: String = checkNotNull(savedStateHandle[TOPIC_NAME])

    companion object {
        const val TOPIC_NAME = "Topic_Name"
        const val TOPIC_ID = "Topic_ID"
        const val CHANNEL_ID = "Channel_Id"

    }
}