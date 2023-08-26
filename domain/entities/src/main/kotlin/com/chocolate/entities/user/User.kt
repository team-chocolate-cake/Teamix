package com.chocolate.entities.user

data class User (
    val imageUrl: String,
    val email: String,
    val fullName: String,
    val role: UserRole,
    val id: Int,
)