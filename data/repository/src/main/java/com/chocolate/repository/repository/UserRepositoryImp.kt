package com.chocolate.repository.repository

import com.chocolate.entities.user.AlertWords
import com.chocolate.entities.user.CreateUser
import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.ProfileData
import com.chocolate.entities.user.Settings
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
import com.chocolate.repository.datastore.PreferencesDataSource
import com.chocolate.repository.mappers.users.toAlertWords
import com.chocolate.repository.mappers.users.toCreateUser
import com.chocolate.repository.mappers.users.toOwnerUser
import com.chocolate.repository.mappers.users.toProfileDataDto
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
import com.chocolate.repository.service.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import repositories.UsersRepositories
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userDataSource: RemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : UsersRepositories {
    override suspend fun getAllUsers(
        clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): Users {
        return userDataSource.getAllUsers(
            clientGravatar, includeCustomProfileFields
        ).toUsers()
    }

    override suspend fun getOwnUser(): OwnerUser {
        return userDataSource.getOwnUser().toOwnerUser()
    }

    override suspend fun getUserById(
        userId: Int, clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): User {
        return userDataSource.getUserById(userId, clientGravatar, includeCustomProfileFields)
            .toUser()
    }

    override suspend fun getUserByEmail(
        email: String, clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): User {
        return userDataSource.getUserByEmail(email, clientGravatar, includeCustomProfileFields)
            .toUser()
    }

    override suspend fun updateUserById(
        id: Int, fullName: String, role: Int, profileData: List<ProfileData>
    ) {
        userDataSource.updateUserById(
            id,
            fullName,
            role,
            profileData.map { it.toProfileDataDto() }
        )
    }

    override suspend fun updateUserStatus(
        statusText: String,
        away: Boolean,
        emojiName: String,
        emojiCode: String,
        reactionType: String
    ) {
        userDataSource.updateUserStatus(
            statusText, away, emojiName, emojiCode, reactionType
        )
    }

    override suspend fun createUser(email: String, password: String, fullName: String): CreateUser {
        return userDataSource.createUser(email, password, fullName).toCreateUser()
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

    override suspend fun setTypingStatus(
        op: String, to: String, type: String, topic: String
    ) {
        userDataSource.setTypingStatus(op, to, type, topic)
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

    override suspend fun updateSettings(settings: Settings): UserSettings {
        return userDataSource.updateSettings(settings.toSettingsDto()).toUserSettings()
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
        return userDataSource.getUserGroupMemberships(groupId, directMemberOnly).toUserGroupMemberships()
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int, directSubgroupOnly: Boolean
    ): SubgroupsOfUserGroup {
        return userDataSource.getSubgroupsOfUserGroup(id, directSubgroupOnly).toSubgroupsOfUserGroup()
    }

    override suspend fun getAlertWords(): AlertWords {
        return userDataSource.getAlertWords().toAlertWords()
    }

    override suspend fun addAlertWords(alertWords: String): AlertWords {
        return userDataSource.addAlertWords(alertWords).toAlertWords()
    }

    override suspend fun removeAlertWords(alertWords: String): AlertWords {
        return userDataSource.removeAlertWords(alertWords).toAlertWords()
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
                it.result == "success" }?.run {
                preferencesDataSource.putAuthenticationData(
                    apikey = apiKey ?: "",
                    email = email ?: ""
                )
                true
            } ?: false
    }

    override suspend fun setUserLoginState(isComplete: Boolean) {
        preferencesDataSource.setUserLoginState(isComplete)
    }

    override suspend fun getUserLoginState(): Flow<Boolean> {
        return preferencesDataSource.currentUserLoginState
    }

}