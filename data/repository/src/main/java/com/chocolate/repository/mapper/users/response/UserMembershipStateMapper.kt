package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.UserMembershipState
import com.chocolate.repository.dto.users.response.UserMembershipStateDto

fun UserMembershipStateDto.toUserMembershipState (): UserMembershipState {
    return UserMembershipState(isUserGroupMember?:false)
}