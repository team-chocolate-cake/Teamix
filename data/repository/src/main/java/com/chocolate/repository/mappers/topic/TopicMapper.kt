package com.chocolate.repository.mappers.topic

import com.chocolate.entities.topic.Topic
import com.chocolate.repository.datastore.realtime.model.TopicDto
import java.util.Date


fun TopicDto.toTopic(): Topic {
    return Topic(
        topicId = topicId ?: "",
        content = content ?: "",
        senderName = senderName ?: "",
        senderImage = senderImage ?: "",
        sentTIme = sentTIme ?: Date(),
    )
}

@JvmName("Message")
fun List<TopicDto>?.toTopic(): List<Topic> = this?.map { it.toTopic() }.orEmpty()

fun Topic.toTopicDto() = TopicDto(
    topicId = topicId,
    content = content,
    senderName = senderName,
    senderImage = senderImage,
    sentTIme = sentTIme,
)

@JvmName("MessageDto")
fun List<Topic>?.toTopicDto(): List<TopicDto> = this?.map { it.toTopicDto() }.orEmpty()
