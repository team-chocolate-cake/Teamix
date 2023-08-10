package com.chocolate.repository.model.dto.draft.response

import com.google.gson.annotations.SerializedName

data class DraftsDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("drafts")
    val drafts: List<DraftDto>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)