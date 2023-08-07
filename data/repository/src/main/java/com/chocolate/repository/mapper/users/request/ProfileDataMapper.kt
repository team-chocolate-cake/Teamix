package com.chocolate.repository.mapper.users.request

import com.chocolate.entities.user.ProfileData
import com.chocolate.repository.dto.users.request.ProfileDataDto

fun ProfileData.toProfileDataDto():ProfileDataDto {

    return ProfileDataDto(id, value)
}