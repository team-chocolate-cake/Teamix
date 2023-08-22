package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.UserInformationSettings
import com.chocolate.repository.model.dto.users.request.SettingsDto

fun UserInformationSettings.toSettingsDto(): SettingsDto {
    return SettingsDto(fullName, email)
}