package com.chocolate.repository.dto.channels.response

import com.google.gson.annotations.SerializedName

data class Subscribed(

    @field:SerializedName("iago@zulip.com")
    val iagoZulipCom: List<String?>? = null
)