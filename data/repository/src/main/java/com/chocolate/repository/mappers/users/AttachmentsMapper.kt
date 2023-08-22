package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.Attachment
import com.chocolate.repository.model.dto.users.response.AttachmentDto

fun AttachmentDto.toEntity(): Attachment {
        return Attachment(
            createTime=createTime ?: 0,
            id=id ?: 0,
            name = name ?: "",
            filePath = pathId ?: "",
            size=size ?: 0
        )
}
fun List<AttachmentDto>?.toEntity(): List<Attachment> = this?.map { it.toEntity() }.orEmpty()