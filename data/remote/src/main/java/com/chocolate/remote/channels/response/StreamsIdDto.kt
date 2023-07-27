package com.chocolate.remote.channels.response

import com.google.gson.annotations.SerializedName

data class StreamsIdDto(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("stream_id")
	val streamId: Int? = null
)
