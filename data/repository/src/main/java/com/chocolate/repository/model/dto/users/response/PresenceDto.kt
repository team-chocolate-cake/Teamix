package com.chocolate.repository.model.dto.users.response

import com.google.gson.annotations.SerializedName

data class PresenceDto(
    @SerializedName("aggregated")
    val aggregatedDto: AggregatedDto?,
    @SerializedName("website")
    val websiteDto: WebsiteDto?
)