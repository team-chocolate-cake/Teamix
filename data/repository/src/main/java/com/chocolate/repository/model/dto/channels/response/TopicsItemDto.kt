package com.chocolate.repository.model.dto.channels.response

import com.google.gson.annotations.SerializedName

data class TopicsItemDto(
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("max_id")
	val maxId: Int? = null
)