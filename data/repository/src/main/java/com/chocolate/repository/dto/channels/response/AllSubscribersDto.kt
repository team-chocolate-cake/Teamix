package com.chocolate.repository.dto.channels.response

import com.google.gson.annotations.SerializedName

data class AllSubscribersDto(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("subscribers")
	val subscribers: List<Int?>? = null
)
