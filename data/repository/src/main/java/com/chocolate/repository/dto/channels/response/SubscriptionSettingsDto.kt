package com.chocolate.repository.dto.channels.response

import com.google.gson.annotations.SerializedName

data class SubscriptionSettingsDto(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("ignored_parameters_unsupported")
	val ignoredParametersUnsupported: List<String?>? = null
)
