package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class Presence(
    @SerializedName("aggregated")
    val aggregated: com.chocolate.repository.dto.remote.users.response.Aggregated?,
    @SerializedName("website")
    val website: com.chocolate.repository.dto.remote.users.response.Website?
)