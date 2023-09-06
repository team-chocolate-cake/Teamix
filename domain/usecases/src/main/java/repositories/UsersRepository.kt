package repositories

import com.chocolate.entities.user.Attachment
import com.chocolate.entities.user.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean>

    suspend fun setUserUsedAppForFirstTime(isComplete: Boolean)

    suspend fun getAllUsers(): List<User>

    suspend fun getRemoteCurrentUser(): User

    suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ): User

    suspend fun getUserByEmail(
        email: String,
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

    suspend fun getUserPresence(email: String): String

    suspend fun getRealmPresence(): String

    suspend fun getAttachments(): List<Attachment>

    suspend fun deleteAttachment(attachmentId: Int)

    //need to review
    suspend fun updateSettings(user: User)

    //suspend fun getUserGroups(): UserGroups

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
    ): List<Int>

    suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ): Boolean

    suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ): List<Int>

    suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ): List<Int>

    suspend fun muteUser(mutedUserId: Int)

    suspend fun unMuteUser(mutedUserId: Int)

    suspend fun userLogin(userName: String, password: String): Boolean

    suspend fun setUserLoginState(isComplete: Boolean)

    suspend fun getUserLoginState(): Flow<Boolean>

    suspend fun clearLoginInformation()

    suspend fun updateAppLanguage(newLanguage: String): Boolean

    suspend fun getLastSelectedAppLanguage(): Flow<String>

    suspend fun updateDarkTheme(isDarkTheme: Boolean)

    suspend fun isDarkThemeEnabled(): Flow<Boolean>

    suspend fun upsertCurrentUser(email: String)

    suspend fun getLocalCurrentUser(): User?

    suspend fun getCurrentUser(): User
}
