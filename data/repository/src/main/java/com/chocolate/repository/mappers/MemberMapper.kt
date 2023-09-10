package com.chocolate.repository.mappers

import com.chocolate.entities.member.Member
import com.chocolate.entities.member.UserRole
import com.chocolate.repository.model.dto.member.MemberDto

@JvmName("memberDtoToMember")
fun MemberDto.toEntity(): Member =
    Member(
        id!!,
        name!!,
        email!!,
        password!!,
        imageUrl!!,
        isActive!!,
        UserRole.fromValue(role!!),
        status!!,
        channelsId
    )

@JvmName("membersDtoToMembers")
fun List<MemberDto>.toEntity(): List<Member> =
    this.map { it.toEntity() }

@JvmName("memberToMemberDto")
fun Member.toRemote(): MemberDto =
    MemberDto(
        id, name, email, password, imageUrl, isActive, status, role.value, channelsId
    )

@JvmName("membersToMembersDto")
fun List<Member>.toRemote(): List<MemberDto> =
    this.map { it.toRemote() }