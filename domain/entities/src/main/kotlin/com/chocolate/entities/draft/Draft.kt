package com.chocolate.entities.draft

data class Draft (
    val content: String,
    val id: Int,
    val timestamp: Double,
    val to: List<Int>,
    val topic: String,
    val type: String

)