package com.chocolate.repository.dto.channels.response

import com.google.gson.annotations.SerializedName

data class AllStreamsDto(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("msg")
	val message: String? = null,

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("streams")
	val streams: List<StreamsItem?>? = null
)

data class StreamsItem(

	@field:SerializedName("stream_id")
	val streamId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("invite_only")
	val inviteOnly: Boolean? = null
)
