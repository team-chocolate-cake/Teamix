package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.UserSettings
import com.chocolate.repository.dto.users.response.UserSettingsDto

fun UserSettingsDto.toUserSettings(): UserSettings {
return UserSettings(ignoredParametersUnsupported?: emptyList())
}