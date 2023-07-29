package com.chocolate.repository.dto.draft.response


import com.google.gson.annotations.SerializedName

data class BaseDraftResponse(
    @SerializedName("ids")
    val ids: List<Int>,
    @SerializedName("code")
    val code: String,
    @SerializedName("msg")
    val message: String,
    @SerializedName("result")
    val result: String
)
