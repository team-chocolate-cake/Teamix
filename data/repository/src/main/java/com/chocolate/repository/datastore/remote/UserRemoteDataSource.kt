package com.chocolate.repository.datastore.remote

import com.chocolate.entities.user.User
import com.chocolate.repository.model.dto.users.response.FetchApiKeyDto
import com.chocolate.repository.model.dto.users.response.MuteUserResponseDto
import com.chocolate.repository.model.dto.users.response.OwnerUserDto
import com.chocolate.repository.model.dto.users.response.ResponseStateDto
import com.chocolate.repository.model.dto.users.response.StatusUserRemoteDto
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

interface UserRemoteDataSource {

    suspend fun getAllUsers(): UsersDto

    suspend fun getOwnUser(): OwnerUserDto

    suspend fun getUserStatus(): StatusUserRemoteDto

    suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): UserDto

    suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean = false,
        includeCustomProfileFields: Boolean = false
    ): UserDto

    suspend fun updateUserById(
        id: Int,
        fullName: String? = null,
        role: Int? = null
    ): ResponseStateDto

    suspend fun deactivateUserAccount(id: Int): ResponseStateDto

    suspend fun reactivateUserAccount(id: Int): ResponseStateDto

    suspend fun deactivateOwnUserAccount(): ResponseStateDto

    suspend fun getUserPresence(email: String): UserStateDto

    suspend fun getRealmPresence(): UsersStateDto

    suspend fun getAttachments(): UserAttachmentsDto

    suspend fun deleteAttachment(attachmentId: Int): ResponseStateDto

    suspend fun updateSettings(user: User): UserSettingsDto

    suspend fun getUserGroups(): UserGroupsDto

    suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): ResponseStateDto

    suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): ResponseStateDto

    suspend fun removeUserGroup(userGroupId: Int): ResponseStateDto

    suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): ResponseStateDto

    suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ): SubgroupsOfUserGroupDto

    suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): UserMembershipStateDto

    suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): UserGroupMembershipsDto

    suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): SubgroupsOfUserGroupDto

    suspend fun muteUser(mutedUserId: Int): MuteUserResponseDto

    suspend fun unMuteUser(mutedUserId: Int): MuteUserResponseDto

    suspend fun fetchApiKey(userName: String, password: String): FetchApiKeyDto
}