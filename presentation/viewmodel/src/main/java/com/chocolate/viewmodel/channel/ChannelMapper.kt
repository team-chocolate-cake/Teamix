package com.chocolate.viewmodel.channel

import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.toStringDate
import java.util.Date

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
