package com.chocolate.repository.dto.channels.response

import com.google.gson.annotations.SerializedName

data class SubscribedStreamDto(

	@field:SerializedName("msg")
	val message: String? = null,
	@field:SerializedName("result")
	val result: String? = null,
	@field:SerializedName("subscriptions")
	val subscriptions: List<SubscriptionsItem?>? = null
)