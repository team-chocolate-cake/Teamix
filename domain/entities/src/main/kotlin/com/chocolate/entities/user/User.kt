package com.chocolate.entities.user

data class User (
    val imageUrl: String,
    val email: String,
    val fullName: String,
    val role: Int,
    val userId: Int,
    val status: String,
    val away: Boolean
)
