package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.PersonalMessageForNarrow
import com.chocolate.repository.model.dto.message.response.PersonalMessageForNarrowDto

fun PersonalMessageForNarrowDto.toPersonalMessageForNarrow(): PersonalMessageForNarrow {
    return PersonalMessageForNarrow(
        firstProcessedId = this.firstProcessedId ?: 0,
        foundNewest = this.foundNewest ?: true,
        foundOldest = this.foundOldest ?: true,
        lastProcessedId = this.lastProcessedId ?: 0,
        processedCount = this.processedCount ?: 0,
        updatedCount = this.updatedCount ?: 0
    )
}