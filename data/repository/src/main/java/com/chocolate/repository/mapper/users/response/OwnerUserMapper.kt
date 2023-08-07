package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.OwnerUser
import com.chocolate.entities.user.respons.ProfileField
import com.chocolate.repository.dto.users.response.OwnerUserDto
import com.chocolate.repository.dto.users.response.ProfileFieldDto

fun OwnerUserDto.toOwnerUser(): OwnerUser {
    return OwnerUser(
        avatarUrl = avatarUrl,
        avatarVersion = avatarVersion,
        dateJoined = dateJoined,
        deliveryEmail = deliveryEmail,
        email = email,
        fullName = fullName,
        isActive = isActive,
        isAdmin = isAdmin,
        isBillingAdmin = isBillingAdmin,
        isBot = isBot,
        isGuest = isGuest,
        isOwner = isOwner,
        maxMessageId = maxMessageId,
        message = message,
        role = role,
        timezone = timezone,
        userId = userId,
        result = result,
        profileData = profileData?.mapValues { it.value.toProfileField() }


    )
}

fun ProfileFieldDto.toProfileField(): ProfileField {
    return ProfileField(
        renderedValue = renderedValue,
        value = value
    )
}

