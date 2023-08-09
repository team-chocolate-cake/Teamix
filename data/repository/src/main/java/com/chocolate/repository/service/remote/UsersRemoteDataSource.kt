package com.chocolate.repository.service.remote

import com.chocolate.entities.user.ProfileData
import com.chocolate.repository.model.dto.users.request.ProfileDataDto
import com.chocolate.repository.model.dto.users.request.SettingsDto
import com.chocolate.repository.model.dto.users.response.AlertWordsDto
import com.chocolate.repository.model.dto.users.response.CreateUserDto
import com.chocolate.repository.model.dto.users.response.FetchApiKeyDto
import com.chocolate.repository.model.dto.users.response.MuteUserResponseDto
import com.chocolate.repository.model.dto.users.response.OwnerUserDto
import com.chocolate.repository.model.dto.users.response.ResponseStateDto
import com.chocolate.repository.model.dto.users.response.SubgroupsOfUserGroupDto
import com.chocolate.repository.model.dto.users.response.UserAttachmentsDto
import com.chocolate.repository.model.dto.users.response.UserDto
import com.chocolate.repository.model.dto.users.response.UserGroupMembershipsDto
import com.chocolate.repository.model.dto.users.response.UserGroupsDto
import com.chocolate.repository.model.dto.users.response.UserMembershipStateDto
import com.chocolate.repository.model.dto.users.response.UserSettingsDto
import com.chocolate.repository.model.dto.users.response.UserStateDto
import com.chocolate.repository.model.dto.users.response.UsersDto
import com.chocolate.repository.model.dto.users.response.UsersStateDto
import retrofit2.Response

interface UsersRemoteDataSource {
    suspend fun getAllUsers(
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): Response<UsersDto>

    suspend fun getOwnUser(): Response<OwnerUserDto>

    suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): Response<UserDto>

    suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): Response<UserDto>

    suspend fun updateUserById(
        id: Int,
        fullName: String? = null,
        role: Int? = null,
        profileData: List<ProfileDataDto>? = null
    ): Response<ResponseStateDto>

    suspend fun updateUserStatus(
        statusText: String? = null,
        away: Boolean? = null,
        emojiName: String? = null,
        emojiCode: String? = null,
        reactionType: String? = null
    ): Response<ResponseStateDto>

    suspend fun createUser(
        email: String,
        password: String,
        fullName: String
    ): Response<CreateUserDto>

    suspend fun deactivateUserAccount(id: Int): Response<ResponseStateDto>

    suspend fun reactivateUserAccount(id: Int): Response<ResponseStateDto>

    suspend fun deactivateOwnUserAccount(): Response<ResponseStateDto>

    suspend fun setTypingStatus(
        op: String,
        to: String,
        type: String? = "direct",
        topic: String? = null
    ): Response<ResponseStateDto>

    suspend fun getUserPresence(email: String): Response<UserStateDto>

    suspend fun getRealmPresence(): Response<UsersStateDto>

    suspend fun getAttachments(): Response<UserAttachmentsDto>

    suspend fun deleteAttachment(attachmentId: Int): Response<ResponseStateDto>

    suspend fun updateSettings(settings: SettingsDto): Response<UserSettingsDto>

    suspend fun getUserGroups(): Response<UserGroupsDto>

    suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): Response<ResponseStateDto>

    suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): Response<ResponseStateDto>

    suspend fun removeUserGroup(userGroupId: Int): Response<ResponseStateDto>

    suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): Response<ResponseStateDto>

    suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ): Response<SubgroupsOfUserGroupDto>

    suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): Response<UserMembershipStateDto>

    suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): Response<UserGroupMembershipsDto>

    suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): Response<SubgroupsOfUserGroupDto>

    suspend fun getAlertWords(): Response<AlertWordsDto>

    suspend fun addAlertWords(alertWords: String): Response<AlertWordsDto>

    suspend fun removeAlertWords(alertWords: String): Response<AlertWordsDto>

    suspend fun muteUser(mutedUserId: Int): Response<MuteUserResponseDto>

    suspend fun unmuteUser(mutedUserId: Int): Response<MuteUserResponseDto>

}
