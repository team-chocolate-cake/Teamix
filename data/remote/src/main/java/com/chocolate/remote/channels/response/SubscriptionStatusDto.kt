package com.chocolate.remote.channels.response

import com.google.gson.annotations.SerializedName

data class SubscriptionStatusDto(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("is_subscribed")
	val isSubscribed: Boolean? = null
)
