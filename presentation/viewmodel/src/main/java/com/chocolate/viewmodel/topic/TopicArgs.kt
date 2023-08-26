package com.chocolate.viewmodel.topic

import androidx.lifecycle.SavedStateHandle

class TopicArgs(savedStateHandle: SavedStateHandle) {
    val topicName: String = checkNotNull(savedStateHandle[TOPIC_NAME])

    companion object {
        const val TOPIC_NAME = "Topic_Name"
    }
}