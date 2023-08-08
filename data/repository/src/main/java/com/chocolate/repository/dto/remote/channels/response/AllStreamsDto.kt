package com.chocolate.repository.dto.channels.response

import com.google.gson.annotations.SerializedName

data class AllStreamsDto(

	@SerializedName("code")
	val code: String? = null,
	@SerializedName("msg")
	val message: String? = null,
	@SerializedName("result")
	val result: String? = null,
	@SerializedName("streams")
	val streams: List<StreamsItem?>? = null
)