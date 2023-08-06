package com.chocolate.entities.user.respons

data class Attachment(

    val createTime: Long?,
    val id: Int?,
    val messages: List<Message?>?,
    val name: String?,
    val pathId: String?,
    val size: Int?
)


data class Message(
    val dateSent: Long?,
    val id: Int?
)
