package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserAttachmentsDto(
    @SerializedName("attachments")
    val attachments: List<Attachment?>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("upload_space_used")
    val uploadSpaceUsed: Int?
)