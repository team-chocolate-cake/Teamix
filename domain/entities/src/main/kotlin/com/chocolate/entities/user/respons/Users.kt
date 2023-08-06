package com.chocolate.entities.user.respons

data class Users(
    val member: List<Member?>?,
)

data class Member(
    val avatarUrl: String?,
    val avatarVersion: Int?,
    val botOwnerId: Int?,
    val botType: Int?,
    val dateJoined: String?,
    val deliveryEmail: String?,
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
