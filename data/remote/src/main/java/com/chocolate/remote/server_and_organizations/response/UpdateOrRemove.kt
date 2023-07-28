package com.chocolate.remote.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class UpdateOrRemove(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)