package com.chocolate.entities

data class Channel(
    val id: String,
    val name: String,
    val description: String,
    val membersId:List<String>
)