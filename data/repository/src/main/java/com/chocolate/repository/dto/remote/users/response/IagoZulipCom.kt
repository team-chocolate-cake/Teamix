package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class IagoZulipCom(
    @SerializedName("aggregated")
    val aggregated: Aggregated?,
    @SerializedName("website")
    val website: Website?
)