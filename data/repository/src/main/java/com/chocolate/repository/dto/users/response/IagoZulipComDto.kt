package com.chocolate.repository.dto.users.response

import com.google.gson.annotations.SerializedName

data class IagoZulipComDto(
    @SerializedName("aggregated")
    val aggregatedDto: AggregatedDto?,
    @SerializedName("website")
    val websiteDto: WebsiteDto?
)