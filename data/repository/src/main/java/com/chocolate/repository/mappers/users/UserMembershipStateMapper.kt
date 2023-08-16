package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.UserMembershipState
import com.chocolate.repository.model.dto.users.response.UserMembershipStateDto

fun UserMembershipStateDto.toUserMembershipState (): UserMembershipState {
    return UserMembershipState(isUserGroupMember?:false)
}