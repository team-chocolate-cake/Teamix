package com.chocolate.remote.channels.response

import com.google.gson.annotations.SerializedName

data class SubscribeToStreamDto(

    @field:SerializedName("msg")
    val msg: String? = null,

    @field:SerializedName("result")
    val result: String? = null,

    @field:SerializedName("subscribed")
    val subscribed: Subscribed? = null,

    @field:SerializedName("already_subscribed")
    val alreadySubscribed: AlreadySubscribed? = null,

    @field:SerializedName("unauthorized")
    val unauthorized:  List<String?>? = null
)

data class Subscribed(

    @field:SerializedName("iago@zulip.com")
    val iagoZulipCom: List<String?>? = null
)

data class AlreadySubscribed(

    @field:SerializedName("newbie@zulip.com")
    val newbieZulipCom: List<String?>? = null
)
