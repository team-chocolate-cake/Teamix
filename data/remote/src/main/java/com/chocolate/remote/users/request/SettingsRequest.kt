package com.chocolate.remote.users.request

import com.google.gson.annotations.SerializedName

data class SettingsRequest(
    @SerializedName("offline_notifications")
    val offlineNotifications: Boolean,
    @SerializedName("online_notifications")
    val onlineNotifications: Boolean,
    @SerializedName("emoji_set")
    val emojiSet: String
)