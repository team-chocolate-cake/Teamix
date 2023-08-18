package repositories

import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.Settings
import com.chocolate.entities.user.SubgroupsOfUserGroup
import com.chocolate.entities.user.User
import com.chocolate.entities.user.UserAttachments
import com.chocolate.entities.user.UserGroupMemberships
import com.chocolate.entities.user.UserGroups
import com.chocolate.entities.user.UserMembershipState
import com.chocolate.entities.user.UserState
import com.chocolate.entities.user.Users
import com.chocolate.entities.user.UsersState
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getAllUsers(
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ): Users

    suspend fun getOwnUser(): OwnerUser

    suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean ,
        includeCustomProfileFields: Boolean
    ): User

    suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean ,
        includeCustomProfileFields: Boolean
    ): User

    //This endpoint is only available to organization administrators.
    suspend fun updateUserById(
        id: Int,
        fullName: String,
        role: Int,
    )

    suspend fun deactivateUserAccount(id: Int)

    suspend fun reactivateUserAccount(id: Int)

    suspend fun deactivateOwnUserAccount()

    suspend fun getUserPresence(email: String): UserState

    suspend fun getRealmPresence(): UsersState

    suspend fun getAttachments(): UserAttachments

    suspend fun deleteAttachment(attachmentId: Int)

    //need to review
    suspend fun updateSettings(settings: Settings)

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

    suspend fun muteUser(mutedUserId: Int)

    suspend fun unMuteUser(mutedUserId: Int)

    suspend fun userLogin(userName: String, password: String): Boolean

    suspend fun setUserLoginState(isComplete: Boolean)

    suspend fun getUserLoginState(): Flow<Boolean>

    suspend fun clearLoginInformation()

    suspend fun updateAppLanguage(newLanguage: String): Boolean

    suspend fun getLastSelectedAppLanguage(): String

    suspend fun updateDarkTheme(isDarkTheme:Boolean):Boolean

    suspend fun isDarkThemeEnabled():Boolean
}
