package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class AlertWordsDTO(
    @SerializedName("alert_words")
    val alertWords: List<String>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)