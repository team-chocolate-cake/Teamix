package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.UserGroup
import com.chocolate.entities.user.UserGroups
import com.chocolate.repository.dto.users.response.UserGroupDto
import com.chocolate.repository.dto.users.response.UserGroupsDto

fun UserGroupsDto.toUserGroups(): UserGroups {
    return UserGroups(userGroupDto?.map { it.toUserGroup() } ?: emptyList())
}

fun UserGroupDto.toUserGroup(): UserGroup {
    return UserGroup(
        description?:"",
        directSubgroupIds?: emptyList(),
        id?:0,
        isSystemGroup?:false,
        members?: emptyList(),
        name?:"",
    )
}