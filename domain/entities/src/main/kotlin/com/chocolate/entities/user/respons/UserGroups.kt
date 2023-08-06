package com.chocolate.entities.user.respons

data class UserGroups (
    val userGroups: List<UserGroup>?
)

data class UserGroup(
    val description: String?,
    val directSubgroupIds: List<Int>?,
    val id: Int?,
    val isSystemGroup: Boolean?,
    val members: List<Int>?,
    val name: String?
)