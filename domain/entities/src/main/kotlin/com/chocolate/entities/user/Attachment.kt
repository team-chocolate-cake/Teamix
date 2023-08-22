package com.chocolate.entities.user

data class Attachment(
    val createTime: Long,
    val id: Int,
    val name: String,
    val filePath: String,
    val size: Int
)
