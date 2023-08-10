package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.UserGroupMemberships
import com.chocolate.repository.model.dto.users.response.UserGroupMembershipsDto

fun UserGroupMembershipsDto.toUserGroupMemberships(): UserGroupMemberships {
return UserGroupMemberships(members?: emptyList())
}