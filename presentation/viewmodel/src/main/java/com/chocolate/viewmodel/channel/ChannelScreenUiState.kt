package com.chocolate.viewmodel.channel

import com.chocolate.entities.Topic
import com.chocolate.entities.utils.Empty
import com.chocolate.entities.utils.toStringDate

data class ChannelScreenUiState(
    val channelName: String = String.Empty,
    val channelId: String = String.Empty,
    val topics: List<TopicState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class TopicState(
    val id: String = String.Empty,
    val creatorName: String = String.Empty,
    val creatorImage: String = String.Empty,
    val topicCreationDate: String = String.Empty,
    val replayImages: List<Int> = emptyList(),
    val topicContent: String = String.Empty,
    val sentTime: String = String.Empty,
)

fun List<Topic>.toUiState(): List<TopicState> =
    map {
        TopicState(
            id = it.topicId,
            creatorName = it.senderName,
            creatorImage = it.senderImage,
            topicCreationDate = it.sentTIme.toString().take(10),
            topicContent = it.content,
            sentTime = it.sentTIme.toStringDate()
        )
    }
