package com.chocolate.remote.channels.response

import com.google.gson.annotations.SerializedName

data class TopicsInStreamDto(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("topics")
	val topics: List<TopicsItem?>? = null
)

data class TopicsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("max_id")
	val maxId: Int? = null
)
