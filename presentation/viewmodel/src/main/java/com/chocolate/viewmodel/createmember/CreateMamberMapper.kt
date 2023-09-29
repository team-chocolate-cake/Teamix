package com.chocolate.viewmodel.createmember

import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.UserRole
import com.chocolate.entities.util.getRandomId

fun CreateMemberUiState.toEntity(role:String) = Member(
    id = getRandomId().toString(),
    name = fullName,
    email = email,
    imageUrl = personalImageUri.toString(),
    password = password,
    role = UserRole.fromValue(role),
    isActive = true,
    status = "",
)