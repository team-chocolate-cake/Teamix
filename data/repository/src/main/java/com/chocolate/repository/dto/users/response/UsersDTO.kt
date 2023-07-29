package com.chocolate.repository.dto.users.response

import com.google.gson.annotations.SerializedName

data class UsersDTO(
    @SerializedName("members")
    val members: List<UserDTO>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
){

    data class UserDTO(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("avatar_version")
        val avatarVersion: Int,
        @SerializedName("bot_owner_id")
        val botOwnerId: Int,
        @SerializedName("bot_type")
        val botType: Int,
        @SerializedName("date_joined")
        val dateJoined: String,
        @SerializedName("delivery_email")
        val deliveryEmail: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("is_active")
        val isActive: Boolean,
        @SerializedName("is_admin")
        val isAdmin: Boolean,
        @SerializedName("is_billing_admin")
        val isBillingAdmin: Boolean,
        @SerializedName("is_bot")
        val isBot: Boolean,
        @SerializedName("is_guest")
        val isGuest: Boolean,
        @SerializedName("is_owner")
        val isOwner: Boolean,
        @SerializedName("role")
        val role: Int,
        @SerializedName("timezone")
        val timezone: String,
        @SerializedName("user_id")
        val userId: Int
    )
}