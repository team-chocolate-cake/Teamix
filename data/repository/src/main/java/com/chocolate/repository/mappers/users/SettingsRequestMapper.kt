package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.Settings
import com.chocolate.repository.model.dto.users.request.SettingsDto

fun Settings.toSettingsRequestDto(): SettingsDto {
return SettingsDto(
    offlineNotifications, onlineNotifications, emojiSet
)
}