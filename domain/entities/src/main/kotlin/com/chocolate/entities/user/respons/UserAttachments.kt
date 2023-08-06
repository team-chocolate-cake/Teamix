package com.chocolate.entities.user.respons

data class UserAttachments(
    val attachment: List<Attachment?>?,
    val uploadSpaceUsed: Int?
)
