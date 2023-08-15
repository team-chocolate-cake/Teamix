package com.chocolate.entities.user

data class OwnerUser (
    val avatarUrl: String,
    val avatarVersion: Int,
    val dateJoined: String,
    val deliveryEmail: String,
    val email: String,
    val fullName: String,
    val isActive: Boolean,
    val isAdmin: Boolean,
    val isBillingAdmin: Boolean,
    val isBot: Boolean,
    val isGuest: Boolean,
    val isOwner: Boolean,
    val maxMessageId: Int,
    val role: Int,
    val timezone: String,
    val userId: Int
)

enum class UserRole(val value: Int, val stringValue: String) {
    OWNER(100, "Owner"),
    ADMIN(200, "Administrator"),
    MODERATOR(300, "Moderator"),
    MEMBER(400, "Member"),
}
