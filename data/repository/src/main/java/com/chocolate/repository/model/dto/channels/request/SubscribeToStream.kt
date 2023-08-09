package com.chocolate.repository.model.dto.channels.request

import com.google.gson.annotations.SerializedName

data class SubscribeToStream(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,
)
