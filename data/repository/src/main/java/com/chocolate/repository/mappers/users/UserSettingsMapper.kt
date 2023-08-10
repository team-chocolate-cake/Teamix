package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.UserSettings
import com.chocolate.repository.model.dto.users.response.UserSettingsDto

fun UserSettingsDto.toUserSettings(): UserSettings {
return UserSettings(ignoredParametersUnsupported?: emptyList())
}