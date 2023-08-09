package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class SettingsDto(
    @SerializedName("enable_offline_push_notifications")
    val offlineNotifications: Boolean,
    @SerializedName("enable_online_push_notifications")
    val onlineNotifications: Boolean,
    @SerializedName("emojiset")
    val emojiSet: String
)