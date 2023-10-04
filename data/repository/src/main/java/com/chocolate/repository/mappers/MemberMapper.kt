package com.chocolate.repository.mappers

import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.UserRole
import com.chocolate.repository.model.dto.MemberDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@JvmName("memberDtoToMember")
fun MemberDto.toEntity(): Member =
    Member(
        id = id!!,
        name = name!!,
        email = email!!,
        password = password!!,
        imageUrl = imageUrl!!,
        isActive = isActive!!,
        role = UserRole.fromValue(role!!),
        status = status!!,
    )

@JvmName("membersDtoToMembers")
fun List<MemberDto>.toEntity(): List<Member> = this.map { it.toEntity() }

@JvmName("flowOfMembersDtoToFlowOfMembers")
fun Flow<List<MemberDto>>.toEntity(): Flow<List<Member>> = this.map { it.toEntity() }

@JvmName("memberToMemberDto")
fun Member.toRemote(): MemberDto =
    MemberDto(
        id = id,
        name = name,
        email = email,
        password = password,
        imageUrl = imageUrl,
        isActive = isActive,
        role = role.value,
        status = status,
    )
