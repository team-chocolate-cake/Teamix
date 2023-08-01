package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class Presences(
    @SerializedName("iago@zulip.com")
    val iagoZulipCom: com.chocolate.repository.dto.remote.users.response.IagoZulipCom?
)