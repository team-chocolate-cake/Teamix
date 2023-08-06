package com.chocolate.repository.mapper.users

import com.chocolate.entities.user.respons.UserSettings
import com.chocolate.repository.dto.users.response.UserSettingsDto

fun UserSettingsDto.toUserSettings(): UserSettings {
return UserSettings(ignoredParametersUnsupported)
}