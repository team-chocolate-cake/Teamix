package com.chocolate.repository.model.dto.channels.response

import com.google.gson.annotations.SerializedName

data class SubscribedDto(

    @field:SerializedName("iago@zulip.com")
    val iagoZulipCom: List<String>? = null
)