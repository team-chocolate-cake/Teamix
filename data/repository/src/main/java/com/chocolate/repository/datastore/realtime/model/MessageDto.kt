package com.chocolate.repository.datastore.realtime.model


data class MessageDto(
    val id: String? = null,
    val text: String? = null,
    val userId: Int? = null,
    val channelId: Int? = null,
    val senderName: String? = null,
    val senderImage:String?=null
)
