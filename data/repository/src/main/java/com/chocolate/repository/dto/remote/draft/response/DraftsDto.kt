package com.chocolate.repository.dto.remote.draft.response


import com.google.gson.annotations.SerializedName

data class DraftsDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("drafts")
    val drafts: List<com.chocolate.repository.dto.remote.draft.response.DraftDto>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)