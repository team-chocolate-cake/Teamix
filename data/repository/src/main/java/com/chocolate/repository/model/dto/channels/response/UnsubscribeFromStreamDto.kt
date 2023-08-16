package com.chocolate.repository.model.dto.channels.response

import com.google.gson.annotations.SerializedName

data class UnsubscribeFromStreamDto(

	@field:SerializedName("code")
	val code: String? = null,
	@field:SerializedName("msg")
	val message: String? = null,
	@field:SerializedName("result")
	val result: String? = null,
	@field:SerializedName("removed")
	val removed: List<String>? = null,
	@field:SerializedName("not_removed")
	val notRemoved: List<String>? = null,
	@field:SerializedName("stream")
	val stream: String? = null,
)
