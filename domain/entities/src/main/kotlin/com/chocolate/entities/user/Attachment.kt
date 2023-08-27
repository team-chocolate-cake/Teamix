package com.chocolate.entities.user

import java.util.Date

data class Attachment(
    val createTime: Date,
    val id: Int,
    val name: String,
    val filePath: String,
    val size: Int
)