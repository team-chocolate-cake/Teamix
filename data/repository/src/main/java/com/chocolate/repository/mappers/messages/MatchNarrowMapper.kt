package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.MatchNarrow
import com.chocolate.repository.dto.remote.message.response.MatchNarrowRemoteDto

fun MatchNarrowRemoteDto.toEntity(): MatchNarrow {
    return MatchNarrow(
        matchContent = this.messages?.messageId?.matchContent ?: "",
        matchSubject = this.messages?.messageId?.matchSubject ?: ""
    )
}