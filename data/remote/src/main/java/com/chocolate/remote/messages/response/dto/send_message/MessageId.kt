package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class MessageId(
    @SerializedName("match_content")
    val matchContent: String?,
    @SerializedName("match_subject")
    val matchSubject: String?
)