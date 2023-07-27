package com.chocolate.remote.drafts.dto


import com.google.gson.annotations.SerializedName

data class DraftsDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("drafts")
    val drafts: List<DraftDto>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)