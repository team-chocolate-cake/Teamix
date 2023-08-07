package com.chocolate.repository.mapper.users.request

import com.chocolate.entities.user.SettingsRequest
import com.chocolate.repository.dto.users.request.SettingsRequestDto

fun SettingsRequest.toSettingsRequestDto(): SettingsRequestDto {
return SettingsRequestDto(
    offlineNotifications, onlineNotifications, emojiSet
)
}