package com.chocolate.entities.user

data class Settings (
    val offlineNotifications: Boolean,
    val onlineNotifications: Boolean,
    val emojiSet: String
)