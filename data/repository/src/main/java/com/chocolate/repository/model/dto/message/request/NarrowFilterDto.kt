package com.chocolate.repository.model.dto.message.request

import com.google.gson.annotations.SerializedName

data class NarrowFilterDto(
    @SerializedName("operand")
    val operand: String,
    @SerializedName("operator")
    val operator: String
)