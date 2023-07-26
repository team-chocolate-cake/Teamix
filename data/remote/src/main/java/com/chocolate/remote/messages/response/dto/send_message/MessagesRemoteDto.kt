package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class MessagesRemoteDto(
    @SerializedName("anchor")
    val anchor: Int?,
    @SerializedName("found_anchor")
    val foundAnchor: Boolean?,
    @SerializedName("found_newest")
    val foundNewest: Boolean?,
    @SerializedName("messages")
    val messages: List<Message?>?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?
)