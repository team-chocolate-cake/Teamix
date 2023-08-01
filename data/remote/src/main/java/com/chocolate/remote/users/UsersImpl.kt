package com.chocolate.remote.users

import com.chocolate.remote.users.service.UsersService
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
import com.chocolate.repository.service.remote.UsersDataSource
import retrofit2.Response
import javax.inject.Inject

class UsersImpl @Inject constructor(
    private val userService: UsersService
): UsersDataSource {
    override suspend fun getAllUsers(
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UsersDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getOwnUser(): Response<com.chocolate.repository.dto.remote.users.response.OwnerUserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserById(
        id: Int,
        fullName: String?,
        role: Int?,
        profileData: List<com.chocolate.repository.dto.remote.users.request.ProfileData>?
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserStatus(
        statusText: String?,
        away: Boolean?,
        emojiName: String?,
        emojiCode: String?,
        reactionType: String?
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(
        email: String,
        password: String,
        fullName: String
    ): Response<com.chocolate.repository.dto.remote.users.response.CreateUserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateUserAccount(id: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun reactivateUserAccount(id: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateOwnUserAccount(): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun setTypingStatus(
        op: String,
        to: String,
        type: String?,
        topic: String?
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPresence(email: String): Response<com.chocolate.repository.dto.remote.users.response.UserStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getRealmPresence(): Response<com.chocolate.repository.dto.remote.users.response.UsersStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAttachments(): Response<com.chocolate.repository.dto.remote.users.response.UserAttachmentsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAttachment(attachmentId: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSettings(settings: com.chocolate.repository.dto.remote.users.request.SettingsRequest): Response<com.chocolate.repository.dto.remote.users.response.UserSettingsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserGroups(): Response<com.chocolate.repository.dto.remote.users.response.UserGroupsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun removeUserGroup(userGroupId: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ): Response<com.chocolate.repository.dto.remote.users.response.SubgroupsOfUserGroupDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UserMembershipStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UserGroupMembershipsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.SubgroupsOfUserGroupDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlertWords(): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addAlertWords(alertWords: String): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun removeAlertWords(alertWords: String): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun muteUser(mutedUserId: Int): Response<com.chocolate.repository.dto.remote.users.response.MuteUserResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun unmuteUser(mutedUserId: Int): Response<com.chocolate.repository.dto.remote.users.response.MuteUserResponseDto> {
        TODO("Not yet implemented")
    }
}