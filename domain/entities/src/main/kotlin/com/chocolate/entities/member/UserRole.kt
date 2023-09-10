package com.chocolate.entities.member

enum class UserRole(val value: String) {
    OWNER("Owner"),
    ADMIN("Administrator"),
    MODERATOR("Moderator"),
    MEMBER("Member"),
    GUEST("Guest");

    companion object {
        fun fromValue(value: String): UserRole {
            return values().find { it.value == value } ?: MEMBER
        }
    }
}