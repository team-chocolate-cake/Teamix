package com.chocolate.entities.user.respons

data class UserDetails(
    val avatarUrl: String?,
    val avatarVersion: Int?,
    val dateJoined: String?,
    val deliveryEmail: Any?,
    val email: String?,
    val fullName: String?,
    val isActive: Boolean?,
    val isAdmin: Boolean?,
    val isBillingAdmin: Boolean?,
    val isBot: Boolean?,
    val isGuest: Boolean?,
    val isOwner: Boolean?,
    val role: Int?,
    val timezone: String?,
    val userId: Int?
)