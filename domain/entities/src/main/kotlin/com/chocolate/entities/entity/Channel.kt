package com.chocolate.entities.entity

data class Channel(
    val id: String,
    val name: String,
    val description: String,
    val membersId:List<String>
)