package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.User
import com.chocolate.entities.user.UserDetails
import com.chocolate.repository.model.dto.users.response.UserDetailsDto
import com.chocolate.repository.model.dto.users.response.UserDto


fun UserDto.toUser(): User {
    return User(
        userDetails= userDetailsDto!!.toUserDetails()
    )
}


fun UserDetailsDto.toUserDetails(): UserDetails {

    return  UserDetails(
        avatarUrl=avatarUrl?:"",
        avatarVersion=avatarVersion?:0,
        dateJoined=dateJoined?:"",
        deliveryEmail=deliveryEmail?:"",
        email=email?:"",
        fullName=fullName?:"",
        isActive=isActive?:false,
        isAdmin=isAdmin?:false,
        isBillingAdmin=isBillingAdmin?:false,
        isBot=isBot?:false,
        isGuest=isGuest?:false,
        isOwner=isOwner?:false,
        role=role?:0,
        timezone=timezone?:"",
        userId=userId?:0)

}