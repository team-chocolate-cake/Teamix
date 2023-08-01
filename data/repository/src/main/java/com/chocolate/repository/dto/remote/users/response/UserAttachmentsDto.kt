package com.chocolate.repository.dto.remote.users.response


import com.google.gson.annotations.SerializedName

data class UserAttachmentsDto(
    @SerializedName("attachments")
    val attachments: List<com.chocolate.repository.dto.remote.users.response.Attachment?>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("upload_space_used")
    val uploadSpaceUsed: Int?
)