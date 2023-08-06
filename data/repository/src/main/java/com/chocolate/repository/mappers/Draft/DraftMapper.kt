package com.chocolate.repository.mappers.Draft

import com.chocolate.entities.draft.Draft
import com.chocolate.repository.dto.draft.response.DraftDto

fun DraftDto.toEntity():Draft = Draft(
    content=content,
    id = id,
    to = to,
    timestamp = timestamp,
    topic = topic,
    type = type,
)
fun List<DraftDto>?.toEntity():List<Draft> = this?.map { it.toEntity() } ?: emptyList()