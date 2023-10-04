package com.chocolate.repository.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.entity.Topic
import com.chocolate.repository.model.dto.TopicDto
import com.chocolate.repository.utils.getCurrentTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

fun Flow<List<TopicDto>>.toEntity(): Flow<List<Topic>> {
    return this.map { it.map { savedTopicDto -> savedTopicDto.toTopic() } }
}

fun TopicDto.toTopic(): Topic {
    return Topic(
        topicId = topicId ?: "",
        name = name ?: "",
        senderName = senderName ?: "",
        senderImage = senderImage ?: "",
        sentTime = sentTIme ?: Date(),
    )
}

@JvmName("Message")
fun List<TopicDto>?.toTopic(): List<Topic> = this?.map { it.toTopic() }.orEmpty()

@RequiresApi(Build.VERSION_CODES.O)
fun Topic.toTopicDto() = TopicDto(
    topicId = topicId,
    name = name,
    senderName = senderName,
    senderImage = senderImage,
    sentTIme = getCurrentTime(),
)

