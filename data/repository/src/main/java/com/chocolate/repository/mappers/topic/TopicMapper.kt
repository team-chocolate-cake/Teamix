package com.chocolate.repository.mappers.topic

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.Topic
import com.chocolate.repository.model.dto.topic.TopicDto
import com.chocolate.repository.utils.getCurrentTime
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

@RequiresApi(Build.VERSION_CODES.O)
fun Topic.toTopicDto() = TopicDto(
    topicId = topicId,
    content = content,
    senderName = senderName,
    senderImage = senderImage,
    sentTIme = getCurrentTime(),
)

@RequiresApi(Build.VERSION_CODES.O)
@JvmName("MessageDto")
fun List<Topic>?.toTopicDto(): List<TopicDto> = this?.map { it.toTopicDto() }.orEmpty()