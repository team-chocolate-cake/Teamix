package com.chocolate.viewmodel.createtopic

import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.Empty
import java.util.Date

data class CreateTopicUiState(
    val name: String = String.Empty,
    val content: String = String.Empty,
    val error: String? = null,
    val isLoading: Boolean = false,
)

fun CreateTopicUiState.toTopic(member: Member): Topic {

    return Topic(
        name = name,
        topicId = "",
        senderName = member.name,
        senderImage = member.imageUrl,
        sentTIme = Date()
    )
}