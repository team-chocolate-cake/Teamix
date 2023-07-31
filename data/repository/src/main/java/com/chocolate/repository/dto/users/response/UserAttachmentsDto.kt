package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserAttachmentsDto(
    @SerializedName("attachments")
    val attachments: List<Attachment?>?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("upload_space_used")
    val uploadSpaceUsed: Int?
) {
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
    ) {
        data class Message(
            @SerializedName("date_sent")
            val dateSent: Long?,
            @SerializedName("id")
            val id: Int?
        )
    }
}
