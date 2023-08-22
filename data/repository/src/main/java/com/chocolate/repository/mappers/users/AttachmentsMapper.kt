package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.Attachment
import com.chocolate.repository.model.dto.users.response.AttachmentDto

fun AttachmentDto.toAttachment(): Attachment {
        return Attachment(
            createTime=createTime ?: 0,
            id=id ?: 0,
            name = name ?: "",
            pathId = pathId ?: "",
            size=size ?: 0
        )
}
fun List<AttachmentDto>?.toAttachments(): List<Attachment> = this?.map { it.toAttachment() }?: emptyList()