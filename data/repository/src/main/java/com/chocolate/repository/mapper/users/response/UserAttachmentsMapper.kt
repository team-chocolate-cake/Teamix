package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.Attachment
import com.chocolate.entities.user.respons.Message
import com.chocolate.entities.user.respons.UserAttachments
import com.chocolate.repository.dto.users.response.AttachmentDto
import com.chocolate.repository.dto.users.response.MessageDto
import com.chocolate.repository.dto.users.response.UserAttachmentsDto

fun  UserAttachmentsDto.toUserAttachments(): UserAttachments {
return UserAttachments(
    attachmentDto?.map { it?.toAttachment() },uploadSpaceUsed
)
}

fun AttachmentDto.toAttachment(): Attachment {
    return Attachment(createTime,id,messageDto?.map { it?.toMessage() },name, pathId, size)
}

fun MessageDto.toMessage(): Message {
    return Message(dateSent, id)
}