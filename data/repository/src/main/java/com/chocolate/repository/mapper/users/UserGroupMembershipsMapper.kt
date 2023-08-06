package com.chocolate.repository.mapper.users

import com.chocolate.entities.user.respons.UserGroupMemberships
import com.chocolate.repository.dto.users.response.UserGroupMembershipsDto

fun UserGroupMembershipsDto.toUserGroupMemberships(): UserGroupMemberships {
return UserGroupMemberships(members)
}