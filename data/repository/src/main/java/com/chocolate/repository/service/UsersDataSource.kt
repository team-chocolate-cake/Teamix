package com.chocolate.repository.service


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
import retrofit2.Response

interface UsersDataSource {
    suspend fun getAllUsers(
        clientGravatar: Boolean? = null,
        includeCustomProfileFields: Boolean? = null
    ): Response<UsersDTO>

    suspend fun getOwnUser(): Response<OwnerUserDTO>

    suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean? = null,
        includeCustomProfileFields: Boolean? = null
    ): Response<UserDTO>

    suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean? = null,
        includeCustomProfileFields: Boolean? = null
    ): Response<UserDTO>

    suspend fun updateUserById(
        id: Int,
        fullName: String? = null,
        role: Int? = null,
        profileData: List<ProfileData>? = null
    ): Response<ResponseStateDTO>

    suspend fun updateUserStatus(
        statusText: String? = null,
        away: Boolean? = null,
        emojiName: String? = null,
        emojiCode: String? = null,
        reactionType: String? = null
    ): Response<ResponseStateDTO>

    suspend fun createUser(
        email: String,
        password: String,
        fullName: String
    ): Response<CreateUserDTO>

    suspend fun deactivateUser(id: Int): Response<ResponseStateDTO>

    suspend fun reactivateUser(id: Int): Response<ResponseStateDTO>

    suspend fun deactivateOwnUser(): Response<ResponseStateDTO>

    suspend fun setTypingStatus(
        op: String,
        to: String,
        type: String? = "direct",
        topic: String? = null
    ): Response<ResponseStateDTO>

    suspend fun getUserPresence(email: String): Response<UserStateDTO>

    suspend fun getRealmPresence(): Response<UsersStateDTO>

    suspend fun getAttachments(): Response<UserAttachmentsDTO>

    suspend fun deleteAttachment(attachmentId: Int): Response<ResponseStateDTO>

    suspend fun updateSettings(settings: SettingsRequest): Response<UserSettingsDTO>

    suspend fun getUserGroups(): Response<UserGroupsDTO>

    suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): Response<ResponseStateDTO>

    suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): Response<ResponseStateDTO>

    suspend fun removeUserGroup(userGroupId: Int): Response<ResponseStateDTO>

    suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): Response<ResponseStateDTO>

    suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ): Response<SubgroupsOfUserGroupDTO>

    suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): Response<UserMembershipStateDTO>

    suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): Response<UserGroupMembershipsDTO>

    suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): Response<SubgroupsOfUserGroupDTO>

    suspend fun getAlertWords(): Response<AlertWordsDTO>

    suspend fun addAlertWords(alertWords: String): Response<AlertWordsDTO>

    suspend fun removeAlertWords(alertWords: String): Response<AlertWordsDTO>

    suspend fun muteUser(mutedUserId: Int): Response<MuteUserResponseDTO>

    suspend fun unmuteUser(mutedUserId: Int): Response<MuteUserResponseDTO>
}
