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
        dateJoined=dateJoined?:"",
        email=email?:"",
        fullName=fullName?:"",
        role=role?:0,
        userId=userId?:0,
    )

}