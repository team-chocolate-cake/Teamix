package com.chocolate.repository.dto.message.response

import com.google.gson.annotations.SerializedName

data class MessageId(
    @SerializedName("match_content")
    val matchContent: String?,
    @SerializedName("match_subject")
    val matchSubject: String?
)