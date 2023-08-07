package com.chocolate.repository.dto.remote.channels.response

import com.google.gson.annotations.SerializedName

data class TopicsItem(
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("max_id")
	val maxId: Int? = null
)