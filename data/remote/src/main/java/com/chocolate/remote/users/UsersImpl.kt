package com.chocolate.remote.users

import com.chocolate.remote.users.service.UsersService
import com.chocolate.repository.dto.users.request.ProfileData
import com.chocolate.repository.dto.users.request.SettingsRequest
import com.chocolate.repository.dto.users.response.AlertWordsDTO
import com.chocolate.repository.dto.users.response.CreateUserDTO
import com.chocolate.repository.dto.users.response.MuteUserResponseDTO
import com.chocolate.repository.dto.users.response.OwnerUserDTO
import com.chocolate.repository.dto.users.response.ResponseStateDTO
import com.chocolate.repository.dto.users.response.SubgroupsOfUserGroupDTO
import com.chocolate.repository.dto.users.response.UserAttachmentsDTO
import com.chocolate.repository.dto.users.response.UserDTO
import com.chocolate.repository.dto.users.response.UserGroupMembershipsDTO
import com.chocolate.repository.dto.users.response.UserGroupsDTO
import com.chocolate.repository.dto.users.response.UserMembershipStateDTO
import com.chocolate.repository.dto.users.response.UserSettingsDTO
import com.chocolate.repository.dto.users.response.UserStateDTO
import com.chocolate.repository.dto.users.response.UsersDTO
import com.chocolate.repository.dto.users.response.UsersStateDTO
import com.chocolate.repository.service.IUsersService
import retrofit2.Response

class UsersImpl(
    private val userService: UsersService
): IUsersService {
    override suspend fun getAllUsers(
        clientGravatar: Boolean?,
        includeCustomProfileFields: Boolean?
    ): Response<UsersDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getOwnUser(): Response<OwnerUserDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean?,
        includeCustomProfileFields: Boolean?
    ): Response<UserDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean?,
        includeCustomProfileFields: Boolean?
    ): Response<UserDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserById(
        id: Int,
        fullName: String?,
        role: Int?,
        profileData: List<ProfileData>?
    ): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserStatus(
        statusText: String?,
        away: Boolean?,
        emojiName: String?,
        emojiCode: String?,
        reactionType: String?
    ): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(
        email: String,
        password: String,
        fullName: String
    ): Response<CreateUserDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateUser(id: Int): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun reactivateUser(id: Int): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateOwnUser(): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun setTypingStatus(
        op: String,
        to: String,
        type: String?,
        topic: String?
    ): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPresence(email: String): Response<UserStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getRealmPresence(): Response<UsersStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getAttachments(): Response<UserAttachmentsDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAttachment(attachmentId: Int): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSettings(settings: SettingsRequest): Response<UserSettingsDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserGroups(): Response<UserGroupsDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun removeUserGroup(userGroupId: Int): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): Response<ResponseStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ): Response<SubgroupsOfUserGroupDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): Response<UserMembershipStateDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): Response<UserGroupMembershipsDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): Response<SubgroupsOfUserGroupDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlertWords(): Response<AlertWordsDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun addAlertWords(alertWords: String): Response<AlertWordsDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun removeAlertWords(alertWords: String): Response<AlertWordsDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun muteUser(mutedUserId: Int): Response<MuteUserResponseDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun unmuteUser(mutedUserId: Int): Response<MuteUserResponseDTO> {
        TODO("Not yet implemented")
    }
}