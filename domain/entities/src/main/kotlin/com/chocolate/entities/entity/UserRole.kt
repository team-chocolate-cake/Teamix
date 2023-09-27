package com.chocolate.entities.entity

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