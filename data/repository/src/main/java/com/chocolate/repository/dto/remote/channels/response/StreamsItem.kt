package com.chocolate.repository.dto.remote.channels.response

import com.google.gson.annotations.SerializedName

data class StreamsItem(
	@SerializedName("stream_id")
	val streamId: Int? = null,
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("description")
	val description: String? = null,
	@SerializedName("invite_only")
	val inviteOnly: Boolean? = null
)