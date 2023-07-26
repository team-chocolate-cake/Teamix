package com.chocolate.remote.messages.request

import com.google.gson.annotations.SerializedName

data class GetMessagesRequest(
    @SerializedName("anchor")
    val anchor: String = "",
    @SerializedName("include_anchor")
    val include_anchor: Boolean = true,
    @SerializedName("num_before")
    val num_before: Int,
    @SerializedName("num_after")
    val num_after:Int,
    @SerializedName("narrow")
    val narrow: List<Narrow>
){
    data class Narrow(val operator: String, val operand: String)
}
