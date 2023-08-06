package repositories.users

import com.chocolate.entities.user.request.ProfileData
import com.chocolate.entities.user.request.SettingsRequest
import com.chocolate.entities.user.respons.AlertWords
import com.chocolate.entities.user.respons.CreateUser
import com.chocolate.entities.user.respons.MuteUserResponse
import com.chocolate.entities.user.respons.OwnerUser
import com.chocolate.entities.user.respons.ResponseState
import com.chocolate.entities.user.respons.SubgroupsOfUserGroup
import com.chocolate.entities.user.respons.User
import com.chocolate.entities.user.respons.UserAttachments
import com.chocolate.entities.user.respons.UserGroupMemberships
import com.chocolate.entities.user.respons.UserGroups
import com.chocolate.entities.user.respons.UserMembershipState
import com.chocolate.entities.user.respons.UserSettings
import com.chocolate.entities.user.respons.UserState
import com.chocolate.entities.user.respons.Users
import com.chocolate.entities.user.respons.UsersState

interface UsersRepositories {

    suspend fun getAllUsers(
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): Users

    suspend fun getOwnUser(): OwnerUser

    suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): User

    suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): User

    suspend fun updateUserById(
        id: Int,
        fullName: String? = null,
        role: Int? = null,
        profileData: List<ProfileData>? = null
    ): ResponseState

    suspend fun updateUserStatus(
        statusText: String? = null,
        away: Boolean? = null,
        emojiName: String? = null,
        emojiCode: String? = null,
        reactionType: String? = null
    ): ResponseState

    suspend fun createUser(
        email: String,
        password: String,
        fullName: String
    ): CreateUser

    suspend fun deactivateUserAccount(id: Int): ResponseState

    suspend fun reactivateUserAccount(id: Int): ResponseState

    suspend fun deactivateOwnUserAccount(): ResponseState

    suspend fun setTypingStatus(
        op: String,
        to: String,
        type: String? = "direct",
        topic: String? = null
    ): ResponseState

    suspend fun getUserPresence(email: String): UserState

    suspend fun getRealmPresence(): UsersState

    suspend fun getAttachments(): UserAttachments

    suspend fun deleteAttachment(attachmentId: Int): ResponseState

    suspend fun updateSettings(settings: SettingsRequest): UserSettings

    suspend fun getUserGroups(): UserGroups

    suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ): ResponseState

    suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ): ResponseState

    suspend fun removeUserGroup(userGroupId: Int): ResponseState

    suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ): ResponseState

    suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>,
        delete: List<Int>
    ): SubgroupsOfUserGroup

    suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): UserMembershipState

    suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): UserGroupMemberships

    suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): SubgroupsOfUserGroup

    suspend fun getAlertWords(): AlertWords

    suspend fun addAlertWords(alertWords: String): AlertWords

    suspend fun removeAlertWords(alertWords: String): AlertWords

    suspend fun muteUser(mutedUserId: Int): MuteUserResponse

    suspend fun unMuteUser(mutedUserId: Int): MuteUserResponse
}
