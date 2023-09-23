package com.chocolate.entities.channel

data class Channel(
    val id: String,
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val membersId:List<String>
)