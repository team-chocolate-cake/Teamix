package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.Attachment
import com.chocolate.entities.user.Message
import com.chocolate.entities.user.UserAttachments
import com.chocolate.repository.model.dto.users.response.AttachmentDto
import com.chocolate.repository.model.dto.users.response.MessageDto
import com.chocolate.repository.model.dto.users.response.UserAttachmentsDto


fun UserAttachmentsDto.toUserAttachments(): UserAttachments {
return UserAttachments(
    attachmentDto?.mapNotNull { it?.toAttachment() } ?: emptyList(),uploadSpaceUsed?:0
)
}

fun AttachmentDto.toAttachment(): Attachment {
        return Attachment(
            createTime ?: 0,
            id ?: 0,
            messageDto?.mapNotNull { it?.toMessage() } ?: emptyList(),
            name ?: "",
            pathId ?: "",
            size ?: 0
        )
}

fun MessageDto.toMessage(): Message {
    return Message(dateSent?:0L, id?:0)
}