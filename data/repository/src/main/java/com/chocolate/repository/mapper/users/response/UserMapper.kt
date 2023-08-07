package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.User
import com.chocolate.entities.user.respons.UserDetails
import com.chocolate.repository.dto.users.response.UserDetailsDto
import com.chocolate.repository.dto.users.response.UserDto

fun UserDto.toUser(): User {
    return User(
        userDetails= userDetailsDto!!.toUserDetails()
    )
}


fun UserDetailsDto.toUserDetails(): UserDetails {

    return  UserDetails(
        avatarUrl=avatarUrl,
        avatarVersion=avatarVersion,
        dateJoined=dateJoined,
        deliveryEmail=deliveryEmail,
        email=email,
        fullName=fullName,
        isActive=isActive,
        isAdmin=isAdmin,
        isBillingAdmin=isBillingAdmin,
        isBot=isBot,
        isGuest=isGuest,
        isOwner=isOwner,
        role=role,
        timezone=timezone,
        userId=userId)

}