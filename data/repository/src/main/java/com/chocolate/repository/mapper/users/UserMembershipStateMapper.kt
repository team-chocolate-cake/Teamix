package com.chocolate.repository.mapper.users

import com.chocolate.entities.user.respons.UserMembershipState
import com.chocolate.repository.dto.users.response.UserMembershipStateDto

fun UserMembershipStateDto.toUserMembershipState (): UserMembershipState {
    return UserMembershipState(isUserGroupMember)
}