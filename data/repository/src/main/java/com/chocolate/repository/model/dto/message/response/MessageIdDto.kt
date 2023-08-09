package com.chocolate.repository.model.dto.message.response

import com.google.gson.annotations.SerializedName

data class MessageIdDto(
    @SerializedName("match_content")
    val matchContent: String?,
    @SerializedName("match_subject")
    val matchSubject: String?
)