package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.ProfileData
import com.chocolate.repository.model.dto.users.request.ProfileDataDto

fun ProfileData.toProfileDataDto(): ProfileDataDto {

    return ProfileDataDto(id, value)
}