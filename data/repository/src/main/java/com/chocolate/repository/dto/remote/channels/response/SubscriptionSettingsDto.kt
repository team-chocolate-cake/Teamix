package com.chocolate.repository.dto.remote.channels.response

import com.google.gson.annotations.SerializedName

data class SubscriptionSettingsDto(

	@field:SerializedName("msg")
	val message: String? = null,
	@field:SerializedName("result")
	val result: String? = null,
	@field:SerializedName("ignored_parameters_unsupported")
	val ignoredParametersUnsupported: List<String>? = null
)
