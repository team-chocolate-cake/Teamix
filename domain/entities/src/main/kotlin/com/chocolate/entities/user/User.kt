package com.chocolate.entities.user

data class User (
    val id: Int,
    val imageUrl: String,
    val email: String,
    val fullName: String,
    val role: UserRole,
)