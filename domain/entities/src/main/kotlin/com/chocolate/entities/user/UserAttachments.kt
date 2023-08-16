package com.chocolate.entities.user

data class UserAttachments(
    val attachment: List<Attachment>,
    val uploadSpaceUsed: Int
)
