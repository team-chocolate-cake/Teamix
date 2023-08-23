package com.chocolate.repository.repository

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
import com.chocolate.repository.datastore.PreferencesDataSource
import com.chocolate.repository.mappers.users.toCurrentUser
import com.chocolate.repository.mappers.users.toCurrentUserLocal
import com.chocolate.repository.mappers.users.toOwnerUser
import com.chocolate.repository.mappers.users.toSettingsDto
import com.chocolate.repository.mappers.users.toSubgroupsOfUserGroup
import com.chocolate.repository.mappers.users.toUser
import com.chocolate.repository.mappers.users.toUserAttachments
import com.chocolate.repository.mappers.users.toUserGroupMemberships
import com.chocolate.repository.mappers.users.toUserGroups
import com.chocolate.repository.mappers.users.toUserMembershipState
import com.chocolate.repository.mappers.users.toUserSettings
import com.chocolate.repository.mappers.users.toUserState
import com.chocolate.repository.mappers.users.toUsers
import com.chocolate.repository.mappers.users.toUsersState
import com.chocolate.repository.service.local.TeamixLocalDataSource
import com.chocolate.repository.service.remote.RemoteDataSource
import repositories.UsersRepository
import javax.inject.Inject
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: RemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val teamixLocalDataSource: TeamixLocalDataSource
) : UsersRepository {
    override suspend fun setUserUsedAppForFirstTime(isComplete: Boolean) {
        preferencesDataSource.setUserUsedAppForFirstTime(isComplete)
    }

    override suspend fun checkIfUserUsedAppOrNot(): Boolean {
        return preferencesDataSource.checkIfUserUsedAppOrNot()
    }

    override suspend fun getAllUsers(
        clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): Users {
        return userDataSource.getAllUsers(
            clientGravatar, includeCustomProfileFields
        ).toUsers()
    }

    override suspend fun getRemoteCurrentUser(): User {
        return userDataSource.getOwnUser().toOwnerUser()
    }

    override suspend fun getUserById(
        userId: Int, clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): User {
        return userDataSource.getUserById(userId, clientGravatar, includeCustomProfileFields)
            .toUser()
    }

    override suspend fun getUserByEmail(
        email: String
    ): User {
        return userDataSource.getUserByEmail(email).toUser()
    }

    override suspend fun updateUserById(
        id: Int, fullName: String, role: Int
    ) {
        userDataSource.updateUserById(
            id,
            fullName,
            role,
        )
    }

    override suspend fun deactivateUserAccount(id: Int) {
        userDataSource.deactivateUserAccount(id)
    }

    override suspend fun reactivateUserAccount(id: Int) {
        userDataSource.reactivateUserAccount(id)
    }

    override suspend fun deactivateOwnUserAccount() {
        userDataSource.deactivateOwnUserAccount()
    }

    override suspend fun getUserPresence(email: String): UserState {
        return userDataSource.getUserPresence(email).toUserState()
    }

    override suspend fun getRealmPresence(): UsersState {
        return userDataSource.getRealmPresence().toUsersState()
    }

    override suspend fun getAttachments(): UserAttachments {
        return userDataSource.getAttachments().toUserAttachments()
    }

    override suspend fun deleteAttachment(attachmentId: Int) {
        userDataSource.deleteAttachment(attachmentId)
    }

    override suspend fun updateSettings(settings: Settings) {
        userDataSource.updateSettings(settings.toSettingsDto()).toUserSettings()
    }

    override suspend fun getUserGroups(): UserGroups {
        return userDataSource.getUserGroups().toUserGroups()
    }

    override suspend fun createUserGroup(
        name: String, description: String, members: String
    ) {
        userDataSource.createUserGroup(name, description, members)
    }

    override suspend fun updateUserGroup(
        userGroupId: Int, name: String, description: String
    ) {
        userDataSource.updateUserGroup(userGroupId, name, description)
    }

    override suspend fun removeUserGroup(userGroupId: Int) {
        userDataSource.removeUserGroup(userGroupId)
    }

    override suspend fun updateUserGroupMembers(
        id: Int, add: List<Int>, delete: List<Int>
    ) {
        userDataSource.updateUserGroupMembers(id, add, delete)
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int, add: List<Int>, delete: List<Int>
    ): SubgroupsOfUserGroup {
        return userDataSource.updateUserGroupSubgroups(userGroupId, add, delete).toSubgroupsOfUserGroup()
    }

    override suspend fun getUserMembership(
        groupId: Int, userId: Int, directMemberOnly: Boolean
    ): UserMembershipState {
        return userDataSource.getUserMembership(groupId, userId, directMemberOnly).toUserMembershipState()
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int, directMemberOnly: Boolean
    ): UserGroupMemberships {
        return userDataSource.getUserGroupMemberships(groupId, directMemberOnly)
            .toUserGroupMemberships()
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int, directSubgroupOnly: Boolean
    ): SubgroupsOfUserGroup {
        return userDataSource.getSubgroupsOfUserGroup(id, directSubgroupOnly)
            .toSubgroupsOfUserGroup()
    }

    override suspend fun muteUser(mutedUserId: Int) {
        userDataSource.muteUser(mutedUserId)
    }

    override suspend fun unMuteUser(mutedUserId: Int) {
            userDataSource.unmuteUser(mutedUserId)
    }

    override suspend fun userLogin(userName: String, password: String): Boolean {
        return userDataSource.fetchApiKey(userName, password)
            .takeIf {
                it.result == "success"
            }?.run {
                preferencesDataSource.setAuthenticationData(
                    apikey = apiKey ?: "",
                    email = email ?: ""
                )
                true
            } ?: false
    }

    override suspend fun setUserLoginState(isComplete: Boolean) {
        preferencesDataSource.setUserLoginState(isComplete)
    }

    override suspend fun getUserLoginState(): Boolean {
        return preferencesDataSource.getCurrentUserLoginState()
    }

    override suspend fun clearLoginInformation() {
        teamixLocalDataSource.deleteDataBase()
        preferencesDataSource.deleteAuthenticationData()
    }

    override suspend fun updateAppLanguage(newLanguage: String): Boolean {
        return preferencesDataSource.upsertAppLanguage(newLanguage)
    }

    override suspend fun getLastSelectedAppLanguage(): String =
        preferencesDataSource.getLastSelectedAppLanguage()

    override suspend fun updateDarkTheme(isDarkTheme: Boolean): Boolean {
        return preferencesDataSource.setDarkThemeValue(isDarkTheme)
    }

    override suspend fun isDarkThemeEnabled(): Boolean {
        return preferencesDataSource.isDarkThemeEnabled()
    }

    override suspend fun upsertCurrentUser(email: String) {
        val user = getUserByEmail(email)
        teamixLocalDataSource.upsertUserData(user.toCurrentUserLocal())
    }

    override suspend fun getLocalCurrentUser(): User? {
        return teamixLocalDataSource.getCurrentUserData()?.toCurrentUser()
    }

    override suspend fun getCurrentUser(): User {
      return  getLocalCurrentUser()
            .takeIf { it != null }
            ?: getRemoteCurrentUser()
                .also { upsertCurrentUser(it.email) }
    }

}

