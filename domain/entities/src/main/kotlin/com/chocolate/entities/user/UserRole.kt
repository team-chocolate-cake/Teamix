package com.chocolate.entities.user

enum class UserRole(val value: Int, val stringValue: String) {
    OWNER(100, "Owner"),
    ADMIN(200, "Administrator"),
    MODERATOR(300, "Moderator"),
    MEMBER(400, "Member"),
}