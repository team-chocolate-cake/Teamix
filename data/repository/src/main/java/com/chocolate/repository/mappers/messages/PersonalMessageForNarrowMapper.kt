package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.PersonalMessageForNarrow
import com.chocolate.repository.dto.message.response.PersonalMessageForNarrowDto

fun PersonalMessageForNarrowDto.toEntity(): PersonalMessageForNarrow {
    return PersonalMessageForNarrow(
        message = this.message ?: "",
        firstProcessedId = this.firstProcessedId ?: 0,
        foundNewest = this.foundNewest ?: true,
        foundOldest = this.foundOldest ?: true,
        lastProcessedId = this.lastProcessedId ?: 0,
        processedCount = this.processedCount ?: 0,
        result = this.result ?: "",
        updatedCount = this.updatedCount ?: 0
    )
}