package com.chocolate.viewmodel.channel

import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.Empty
import com.chocolate.entities.util.toStringDate
import java.util.Date

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
    val topicContent: String = String.Empty,
    val sentTime: String = String.Empty,
)

fun List<Topic>.toUiState(): List<TopicState> {
    return map {
        TopicState(
            id = it.topicId,
            creatorName = if (it.senderName.length < 13) it.senderName else "${it.senderName.take(12)}...",
            creatorImage = it.senderImage,
            topicContent = it.name,
            sentTime = it.sentTime.toStringDate()
        )
    }
}


fun TopicState.toTopic(): Topic {
    return Topic(
        topicId = id,
        senderName = creatorName,
        senderImage = creatorImage,
        name = topicContent,
        sentTime = Date()
    )
}



