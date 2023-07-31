package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserGroupsDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user_groups")
    val userGroups: List<UserGroup>?
) {
    data class UserGroup(
        @SerializedName("description")
        val description: String?,
        @SerializedName("direct_subgroup_ids")
        val directSubgroupIds: List<Int>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("is_system_group")
        val isSystemGroup: Boolean?,
        @SerializedName("members")
        val members: List<Int>?,
        @SerializedName("name")
        val name: String?
    )
}
