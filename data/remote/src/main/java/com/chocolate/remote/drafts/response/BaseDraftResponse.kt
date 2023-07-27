package com.chocolate.remote.drafts.response


import com.google.gson.annotations.SerializedName

data class BaseDraftResponse(
    @SerializedName("ids")
    val ids: List<Int>,
    @SerializedName("code")
    val code: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)