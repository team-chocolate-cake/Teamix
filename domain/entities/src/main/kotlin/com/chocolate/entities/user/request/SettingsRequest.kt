package com.chocolate.entities.user.request

data class SettingsRequest (
    val offlineNotifications: Boolean,
    val onlineNotifications: Boolean,
    val emojiSet: String
)