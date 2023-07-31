package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user")
    val user: User?
) {
    data class User(
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("avatar_version")
        val avatarVersion: Int?,
        @SerializedName("date_joined")
        val dateJoined: String?,
        @SerializedName("delivery_email")
        val deliveryEmail: Any?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("full_name")
        val fullName: String?,
        @SerializedName("is_active")
        val isActive: Boolean?,
        @SerializedName("is_admin")
        val isAdmin: Boolean?,
        @SerializedName("is_billing_admin")
        val isBillingAdmin: Boolean?,
        @SerializedName("is_bot")
        val isBot: Boolean?,
        @SerializedName("is_guest")
        val isGuest: Boolean?,
        @SerializedName("is_owner")
        val isOwner: Boolean?,
        @SerializedName("role")
        val role: Int?,
        @SerializedName("timezone")
        val timezone: String?,
        @SerializedName("user_id")
        val userId: Int?
    )
}