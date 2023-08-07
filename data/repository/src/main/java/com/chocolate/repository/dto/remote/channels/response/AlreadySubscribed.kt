package com.chocolate.repository.dto.remote.channels.response

import com.google.gson.annotations.SerializedName

data class AlreadySubscribed(

    @field:SerializedName("newbie@zulip.com")
    val newbieZulipCom: List<String?>? = null
)