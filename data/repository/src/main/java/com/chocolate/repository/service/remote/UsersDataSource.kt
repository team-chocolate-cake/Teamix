package com.chocolate.repository.service.remote


import com.chocolate.repository.dto.remote.users.request.ProfileData
import com.chocolate.repository.dto.remote.users.request.SettingsRequest
import com.chocolate.repository.dto.remote.users.response.AlertWordsDto
import com.chocolate.repository.dto.remote.users.response.CreateUserDto
import com.chocolate.repository.dto.remote.users.response.MuteUserResponseDto
import com.chocolate.repository.dto.remote.users.response.OwnerUserDto
import com.chocolate.repository.dto.remote.users.response.ResponseStateDto
import com.chocolate.repository.dto.remote.users.response.SubgroupsOfUserGroupDto
import com.chocolate.repository.dto.remote.users.response.UserAttachmentsDto
import com.chocolate.repository.dto.remote.users.response.UserDto
import com.chocolate.repository.dto.remote.users.response.UserGroupMembershipsDto
import com.chocolate.repository.dto.remote.users.response.UserGroupsDto
import com.chocolate.repository.dto.remote.users.response.UserMembershipStateDto
import com.chocolate.repository.dto.remote.users.response.UserSettingsDto
import com.chocolate.repository.dto.remote.users.response.UserStateDto
import com.chocolate.repository.dto.remote.users.response.UsersDto
import com.chocolate.repository.dto.remote.users.response.UsersStateDto
import retrofit2.Response

interface UsersDataSource {
    suspend fun getAllUsers(
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): Response<com.chocolate.repository.dto.remote.users.response.UsersDto>

    suspend fun getOwnUser(): Response<com.chocolate.repository.dto.remote.users.response.OwnerUserDto>

    suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): Response<com.chocolate.repository.dto.remote.users.response.UserDto>

    suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): Response<com.chocolate.repository.dto.remote.users.response.UserDto>

    suspend fun updateUserById(
        id: Int,
        fullName: String? = null,
        role: Int? = null,
        profileData: List<com.chocolate.repository.dto.remote.users.request.ProfileData>? = null
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun updateUserStatus(
        statusText: String? = null,
        away: Boolean? = null,
        emojiName: String? = null,
        emojiCode: String? = null,
        reactionType: String? = null
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun createUser(
        email: String,
        password: String,
        fullName: String
    ): Response<com.chocolate.repository.dto.remote.users.response.CreateUserDto>

    suspend fun deactivateUserAccount(id: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun reactivateUserAccount(id: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun deactivateOwnUserAccount(): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun setTypingStatus(
        op: String,
        to: String,
        type: String? = "direct",
        topic: String? = null
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun getUserPresence(email: String): Response<com.chocolate.repository.dto.remote.users.response.UserStateDto>

    suspend fun getRealmPresence(): Response<com.chocolate.repository.dto.remote.users.response.UsersStateDto>

    suspend fun getAttachments(): Response<com.chocolate.repository.dto.remote.users.response.UserAttachmentsDto>

    suspend fun deleteAttachment(attachmentId: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun updateSettings(settings: com.chocolate.repository.dto.remote.users.request.SettingsRequest): Response<com.chocolate.repository.dto.remote.users.response.UserSettingsDto>

    suspend fun getUserGroups(): Response<com.chocolate.repository.dto.remote.users.response.UserGroupsDto>

    suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun removeUserGroup(userGroupId: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ): Response<com.chocolate.repository.dto.remote.users.response.SubgroupsOfUserGroupDto>

    suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UserMembershipStateDto>

    suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UserGroupMembershipsDto>

    suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.SubgroupsOfUserGroupDto>

    suspend fun getAlertWords(): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto>

    suspend fun addAlertWords(alertWords: String): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto>

    suspend fun removeAlertWords(alertWords: String): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto>

    suspend fun muteUser(mutedUserId: Int): Response<com.chocolate.repository.dto.remote.users.response.MuteUserResponseDto>

    suspend fun unmuteUser(mutedUserId: Int): Response<com.chocolate.repository.dto.remote.users.response.MuteUserResponseDto>
}
