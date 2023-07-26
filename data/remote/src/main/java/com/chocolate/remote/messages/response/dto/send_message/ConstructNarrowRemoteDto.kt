package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class ConstructNarrowRemoteDto(
    @SerializedName("operand")
    val operand: Int?,
    @SerializedName("operator")
    val `operator`: String?
)