package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class MessagesDto(
    @SerializedName("anchor")
    val anchor: Int?,
    @SerializedName("found_anchor")
    val foundAnchor: Boolean?,
    @SerializedName("found_newest")
    val foundNewest: Boolean?,
    @SerializedName("messages")
    val messages: List<Message>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)