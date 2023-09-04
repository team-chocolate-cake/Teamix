package com.chocolate.entities.user

data class Member(
    val id: String,
    val name: String,
    val imageUrl: String,
    val isActive: Boolean,
    val email: String,
    val status: String,
    val role: UserRole,
)
