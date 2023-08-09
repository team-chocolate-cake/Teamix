package com.chocolate.repository.dto.remote.channels.response

import com.google.gson.annotations.SerializedName

data class TopicsInStreamDto(

	@field:SerializedName("code")
	val code: String? = null,
	@field:SerializedName("msg")
	val message: String? = null,
	@field:SerializedName("result")
	val result: String? = null,
	@field:SerializedName("topics")
	val topics: List<TopicsItemDto>? = null
)