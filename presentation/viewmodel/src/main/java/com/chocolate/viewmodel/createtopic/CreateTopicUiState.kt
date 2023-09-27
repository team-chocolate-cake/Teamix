package com.chocolate.viewmodel.createtopic

import com.chocolate.entities.Member
import com.chocolate.entities.Topic
import com.chocolate.entities.utils.Empty
import java.util.Date

data class CreateTopicUiState(
    val name: String = String.Empty,
    val content: String = String.Empty,
    val message: String? = null,
    val isLoading: Boolean = false,
)

fun CreateTopicUiState.toTopic(member: Member): Topic {

    return Topic(
        content = content,
        topicId = "",
        senderName = member.name,
        senderImage = member.imageUrl,
        sentTIme = Date()
    )
}