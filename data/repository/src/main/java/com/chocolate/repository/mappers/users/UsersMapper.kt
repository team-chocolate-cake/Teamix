package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.Member
import com.chocolate.entities.user.Users
import com.chocolate.repository.model.dto.users.response.MemberDto
import com.chocolate.repository.model.dto.users.response.UsersDto


fun UsersDto.toUsers(): Users {
    return Users(
        member=memberDto?.mapNotNull { it?.toMember() }?: emptyList()
    )
}


fun MemberDto.toMember(): Member {

    return  Member(
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
        userId=userId?:0,
        botOwnerId =botOwnerId?:0 ,
        botType = botType?:0,

    )

}