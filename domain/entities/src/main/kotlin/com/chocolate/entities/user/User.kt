package com.chocolate.entities.user

data class User (
    val imageUrl: String = "",
    val email: String = "",
    val fullName: String = "",
    val role: Int = 0,
    val userId: Int = 0,
    val status: String = "",
    val away: Boolean = false
)
