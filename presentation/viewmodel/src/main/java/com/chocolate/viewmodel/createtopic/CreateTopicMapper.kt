package com.chocolate.viewmodel.createtopic

import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.Topic
import java.util.Date

fun CreateTopicUiState.toTopic(member: Member): Topic {
    return Topic(
        name = name,
        topicId = "",
        senderName = member.name,
        senderImage = member.imageUrl,
        sentTime = Date()
    )
}