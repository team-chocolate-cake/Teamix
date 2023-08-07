package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.ProfileField
import com.chocolate.repository.dto.users.response.OwnerUserDto
import com.chocolate.repository.dto.users.response.ProfileFieldDto

fun OwnerUserDto.toOwnerUser(): OwnerUser {
    return OwnerUser(
        avatarUrl = avatarUrl?:"",
        avatarVersion = avatarVersion?:0,
        dateJoined = dateJoined?:"",
        deliveryEmail = deliveryEmail?:"",
        email = email?:"",
        fullName = fullName?:"",
        isActive = isActive?:false,
        isAdmin = isAdmin?:false,
        isBillingAdmin = isBillingAdmin?:false,
        isBot = isBot?:false,
        isGuest = isGuest?:false,
        isOwner = isOwner?:false,
        maxMessageId = maxMessageId?:0,
        role = role?:0,
        timezone = timezone?:"",
        userId = userId?:0,
        profileData = profileData?.mapValues { it.value.toProfileField() }?: emptyMap()


    )
}

fun ProfileFieldDto.toProfileField(): ProfileField {
    return ProfileField(
        renderedValue = renderedValue?:"",
        value = value?:""
    )
}

