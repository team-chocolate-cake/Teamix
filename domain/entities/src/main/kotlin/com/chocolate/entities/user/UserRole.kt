package com.chocolate.entities.user

enum class UserRole(val value: Int, val stringValue: String) {
    OWNER(100, "Owner"),
    ADMIN(200, "Administrator"),
    MODERATOR(300, "Moderator"),
    MEMBER(400, "Member"),
    GUEST(600,"Guest");

    companion object {
        fun fromValue(value: Int): UserRole {
            return values().find { it.value == value } ?: MEMBER
        }

        fun getIntValueByString(stringValue: String): Int? {
            return values().find { it.stringValue == stringValue }?.value
        }
    }
}