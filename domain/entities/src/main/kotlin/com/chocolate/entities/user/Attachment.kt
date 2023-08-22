package com.chocolate.entities.user

data class Attachment(
    val createTime: Long,
    val id: Int,
    val name: String,
    val pathId: String,
    val size: Int
)
