package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.UserGroup
import com.chocolate.entities.user.respons.UserGroups
import com.chocolate.repository.dto.users.response.UserGroupDto
import com.chocolate.repository.dto.users.response.UserGroupsDto

fun UserGroupsDto.toUserGroups(): UserGroups {
    return UserGroups(userGroupDto?.map { it.toUserGroup() })
}

fun UserGroupDto.toUserGroup(): UserGroup {
    return UserGroup(
        description,
        directSubgroupIds,
        id,
        isSystemGroup,
        members,
        name,
    )
}