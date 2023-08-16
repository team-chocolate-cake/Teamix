package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.SubgroupsOfUserGroup
import com.chocolate.repository.model.dto.users.response.SubgroupsOfUserGroupDto

fun SubgroupsOfUserGroupDto.toSubgroupsOfUserGroup(): SubgroupsOfUserGroup {
return SubgroupsOfUserGroup(
    subgroups?: emptyList()
)
}