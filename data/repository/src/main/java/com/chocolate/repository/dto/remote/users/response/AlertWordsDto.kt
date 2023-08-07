package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class AlertWordsDto(
    @SerializedName("alert_words")
    val alertWords: List<String>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)