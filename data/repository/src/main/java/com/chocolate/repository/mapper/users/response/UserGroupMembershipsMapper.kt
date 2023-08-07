package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.UserGroupMemberships
import com.chocolate.repository.dto.users.response.UserGroupMembershipsDto

fun UserGroupMembershipsDto.toUserGroupMemberships(): UserGroupMemberships {
return UserGroupMemberships(members?: emptyList())
}