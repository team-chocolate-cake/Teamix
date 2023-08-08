package com.chocolate.repository.dto.users.response

import com.google.gson.annotations.SerializedName

data class Presences(
    @SerializedName("iago@zulip.com")
    val iagoZulipCom: IagoZulipCom?
)