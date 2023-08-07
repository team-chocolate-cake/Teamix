package repositories.users

import com.chocolate.entities.user.ProfileData
import com.chocolate.entities.user.SettingsRequest
import com.chocolate.entities.user.AlertWords
import com.chocolate.entities.user.CreateUser
import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.SubgroupsOfUserGroup
import com.chocolate.entities.user.User
import com.chocolate.entities.user.UserAttachments
import com.chocolate.entities.user.UserGroupMemberships
import com.chocolate.entities.user.UserGroups
import com.chocolate.entities.user.UserMembershipState
import com.chocolate.entities.user.UserSettings
import com.chocolate.entities.user.UserState
import com.chocolate.entities.user.Users
import com.chocolate.entities.user.UsersState

interface UsersRepositories {

    suspend fun getAllUsers(
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): Users

    suspend fun getOwnUser(): OwnerUser

    suspend fun getUserById(
        userId: Int=635418,
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): User



    suspend fun getUserByEmail(
        email: String="user635418@chocolate-cake.zulipchat.com",
        clientGravatar: Boolean = true,
        includeCustomProfileFields: Boolean = false
    ): User
    //This endpoint is only available to organization administrators.
    suspend fun updateUserById(
        id: Int=635418,
        fullName: String? = null,
        role: Int? = null,
        profileData: List<ProfileData>? = null
    )

    suspend fun updateUserStatus(
        statusText: String? = "good",
        away: Boolean? = null,
        emojiName: String? = null,
        emojiCode: String? = null,
        reactionType: String? = null
    )

    //This endpoint is only available to organization administrators.
    suspend fun createUser(
        email: String,
        password: String,
        fullName: String
    ): CreateUser

    suspend fun deactivateUserAccount(id: Int)

    suspend fun reactivateUserAccount(id: Int)

    suspend fun deactivateOwnUserAccount()

    suspend fun setTypingStatus(
        op: String,
        to: String,
        type: String? = "direct",
        topic: String? = null
    )

    suspend fun getUserPresence(email: String): UserState

    suspend fun getRealmPresence(): UsersState

    suspend fun getAttachments(): UserAttachments

    suspend fun deleteAttachment(attachmentId: Int)

    suspend fun updateSettings(settings: SettingsRequest): UserSettings

    suspend fun getUserGroups(): UserGroups

    suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    )

    suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    )

    suspend fun removeUserGroup(userGroupId: Int)

    suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    )

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

    suspend fun muteUser(mutedUserId: Int)

    suspend fun unMuteUser(mutedUserId: Int)
}
