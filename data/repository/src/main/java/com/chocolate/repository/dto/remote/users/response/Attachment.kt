package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("create_time")
    val createTime: Long?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("messages")
    val messages: List<Message?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path_id")
    val pathId: String?,
    @SerializedName("size")
    val size: Int?
)