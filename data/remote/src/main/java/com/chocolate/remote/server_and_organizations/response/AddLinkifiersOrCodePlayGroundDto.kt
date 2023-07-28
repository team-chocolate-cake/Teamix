package com.chocolate.remote.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class AddLinkifiersOrCodePlayGroundDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)