package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.Member
import com.chocolate.entities.user.respons.Users
import com.chocolate.repository.dto.users.response.MemberDto
import com.chocolate.repository.dto.users.response.UsersDto

fun UsersDto.toUsers(): Users {
    return Users(
        member=memberDto?.map { it?.toMember() }
    )
}


fun MemberDto.toMember(): Member {

    return  Member(
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
        userId=userId,
        botOwnerId =botOwnerId ,
        botType = botType,

    )

}