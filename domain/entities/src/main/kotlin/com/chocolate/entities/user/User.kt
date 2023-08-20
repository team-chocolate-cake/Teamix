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

enum class UserRole(val value: Int, val stringValue: String) {
    OWNER(100, "Owner"),
    ADMIN(200, "Administrator"),
    MODERATOR(300, "Moderator"),
    MEMBER(400, "Member"),
}
