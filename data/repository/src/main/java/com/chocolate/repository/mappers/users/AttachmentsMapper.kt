package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.Attachment
import com.chocolate.repository.mappers.toDate
import com.chocolate.repository.model.dto.users.response.AttachmentDto
import java.util.Date

fun AttachmentDto.toEntity(): Attachment {
    return Attachment(
        createTime = createTime?.toDate() ?: Date(),
        id = id ?: 0,
        name = name.orEmpty(),
        filePath = pathId.orEmpty(),
        size = size ?: 0
    )
}

fun List<AttachmentDto>?.toEntity(): List<Attachment> = this?.map { it.toEntity() }.orEmpty()