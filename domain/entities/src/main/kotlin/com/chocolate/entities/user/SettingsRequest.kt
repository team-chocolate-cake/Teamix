package com.chocolate.entities.user

data class SettingsRequest (
    val offlineNotifications: Boolean,
    val onlineNotifications: Boolean,
    val emojiSet: String
)