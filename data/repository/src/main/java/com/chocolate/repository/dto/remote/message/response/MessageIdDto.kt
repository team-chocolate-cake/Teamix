package com.chocolate.repository.dto.remote.message.response

import com.google.gson.annotations.SerializedName

data class MessageIdDto(
    @SerializedName("match_content")
    val matchContent: String?,
    @SerializedName("match_subject")
    val matchSubject: String?
)