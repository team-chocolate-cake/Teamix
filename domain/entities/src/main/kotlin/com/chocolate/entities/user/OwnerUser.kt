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
    val profileData: Map<String, ProfileField>,
    val role: Int,
    val timezone: String,
    val userId: Int
)

data class ProfileField(
    val renderedValue: String ,
    val value: String
)