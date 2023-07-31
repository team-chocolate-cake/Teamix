package com.chocolate.repository.dto.channels.response

import com.google.gson.annotations.SerializedName

data class SubscriptionStatusDto(

	@field:SerializedName("msg")
	val message: String? = null,
	@field:SerializedName("result")
	val result: String? = null,
	@field:SerializedName("is_subscribed")
	val isSubscribed: Boolean? = null
)
