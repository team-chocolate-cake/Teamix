package com.chocolate.repository.model.dto.channels.response

import com.google.gson.annotations.SerializedName

data class SubscribeToStreamDto(

    @field:SerializedName("msg")
    val message: String? = null,
    @field:SerializedName("result")
    val result: String? = null,
    @field:SerializedName("subscribed")
    val subscribedDto: SubscribedDto? = null,
    @field:SerializedName("already_subscribed")
    val alreadySubscribedDto: AlreadySubscribedDto? = null,
    @field:SerializedName("unauthorized")
    val unauthorized:  List<String>? = null
)