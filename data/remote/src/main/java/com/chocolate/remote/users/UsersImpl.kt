package com.chocolate.remote.users

import com.chocolate.remote.users.service.UsersService
import com.chocolate.repository.dto.users.request.ProfileData
import com.chocolate.repository.dto.users.request.SettingsRequest
import com.chocolate.repository.dto.users.response.AlertWordsDto
import com.chocolate.repository.dto.users.response.CreateUserDto
import com.chocolate.repository.dto.users.response.MuteUserResponseDto
import com.chocolate.repository.dto.users.response.OwnerUserDto
import com.chocolate.repository.dto.users.response.ResponseStateDto
import com.chocolate.repository.dto.users.response.SubgroupsOfUserGroupDto
import com.chocolate.repository.dto.users.response.UserAttachmentsDto
import com.chocolate.repository.dto.users.response.UserDto
import com.chocolate.repository.dto.users.response.UserGroupMembershipsDto
import com.chocolate.repository.dto.users.response.UserGroupsDto
import com.chocolate.repository.dto.users.response.UserMembershipStateDto
import com.chocolate.repository.dto.users.response.UserSettingsDto
import com.chocolate.repository.dto.users.response.UserStateDto
import com.chocolate.repository.dto.users.response.UsersDto
import com.chocolate.repository.dto.users.response.UsersStateDto
import com.chocolate.repository.service.UsersDataSource
import retrofit2.Response
import javax.inject.Inject

class UsersImpl @Inject constructor(
    private val userService: UsersService
): UsersDataSource {
    override suspend fun getAllUsers(
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ): Response<UsersDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getOwnUser(): Response<OwnerUserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ): Response<UserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ): Response<UserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserById(
        id: Int,
        fullName: String?,
        role: Int?,
        profileData: List<ProfileData>?
    ): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserStatus(
        statusText: String?,
        away: Boolean?,
        emojiName: String?,
        emojiCode: String?,
        reactionType: String?
    ): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(
        email: String,
        password: String,
        fullName: String
    ): Response<CreateUserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateUser(id: Int): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun reactivateUser(id: Int): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateOwnUser(): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun setTypingStatus(
        op: String,
        to: String,
        type: String?,
        topic: String?
    ): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPresence(email: String): Response<UserStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getRealmPresence(): Response<UsersStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAttachments(): Response<UserAttachmentsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAttachment(attachmentId: Int): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSettings(settings: SettingsRequest): Response<UserSettingsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserGroups(): Response<UserGroupsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun removeUserGroup(userGroupId: Int): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): Response<ResponseStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ): Response<SubgroupsOfUserGroupDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): Response<UserMembershipStateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): Response<UserGroupMembershipsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): Response<SubgroupsOfUserGroupDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlertWords(): Response<AlertWordsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addAlertWords(alertWords: String): Response<AlertWordsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun removeAlertWords(alertWords: String): Response<AlertWordsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun muteUser(mutedUserId: Int): Response<MuteUserResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun unmuteUser(mutedUserId: Int): Response<MuteUserResponseDto> {
        TODO("Not yet implemented")
    }
}