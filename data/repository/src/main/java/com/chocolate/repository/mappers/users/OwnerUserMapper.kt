package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.OwnerUser
import com.chocolate.repository.model.dto.users.response.OwnerUserDto

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
    )
}