package com.chocolate.repository.model.dto.users.response

import com.google.gson.annotations.SerializedName

data class IagoZulipCom(
    @SerializedName("aggregated")
    val aggregated: Aggregated?,
    @SerializedName("website")
    val website: Website?
)