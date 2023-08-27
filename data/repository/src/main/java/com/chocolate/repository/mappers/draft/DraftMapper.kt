package com.chocolate.repository.mappers.draft

import com.chocolate.entities.draft.Draft
import com.chocolate.repository.mappers.toDate
import com.chocolate.repository.model.dto.draft.response.DraftDto

fun DraftDto.toEntity(): Draft = Draft(
    content=content,
    id = id,
    timestamp = timestamp.toDate(),
    topic = topic,
    targetAudienceIDs = to,
    isInStream = false
)
fun List<DraftDto>?.toEntity():List<Draft> = this?.map { it.toEntity() }.orEmpty()