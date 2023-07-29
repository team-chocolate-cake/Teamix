package com.chocolate.repository.dto.channels.request

import com.google.gson.annotations.SerializedName

data class SubscriptionSettings(

    @SerializedName("stream_id") val streamId: Int,

    @SerializedName("property") val property: String,

    @SerializedName("value") val value: Any,
)
