package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.SubgroupsOfUserGroup
import com.chocolate.repository.dto.users.response.SubgroupsOfUserGroupDto

fun SubgroupsOfUserGroupDto.toSubgroupsOfUserGroup(): SubgroupsOfUserGroup {
return SubgroupsOfUserGroup(
    subgroups
)
}