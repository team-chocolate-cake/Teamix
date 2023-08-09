package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.MessageEditHistory
import com.chocolate.repository.dto.remote.message.response.MessageEditHistoryDto

fun MessageEditHistoryDto.toMessageEditHistory(): List<MessageEditHistory> {
    return this.messageHistory?.map { messageHistory ->
        MessageEditHistory(
            content = messageHistory.content ?: "",
            prevContent = messageHistory.prevContent ?: "",
            prevRenderedContent = messageHistory.prevRenderedContent ?: "",
            prevTopic = messageHistory.prevTopic ?: "",
            renderedContent = messageHistory.renderedContent ?: "",
            timestamp = messageHistory.timestamp ?: 0,
            topic = messageHistory.topic ?: "",
            userId = messageHistory.userId ?: 0
        )
    } ?: emptyList()
}
