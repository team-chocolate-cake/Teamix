package com.chocolate.repository.repository

import com.chocolate.entities.user.ProfileData
import com.chocolate.entities.user.Settings
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
import com.chocolate.repository.datastore.UserDataStoreDataSource
import com.chocolate.repository.mappers.users.toCreateUser
import com.chocolate.repository.mappers.users.toOwnerUser
import com.chocolate.repository.mappers.users.toProfileDataDto
import com.chocolate.repository.mappers.users.toSettingsDto
import com.chocolate.repository.mappers.users.toAlertWords
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

import repositories.UsersRepositories
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userDataSource: RemoteDataSource,
    private val userDataStoreDataSource: UserDataStoreDataSource
) : UsersRepositories, BaseRepository() {
    override suspend fun getAllUsers(
        clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): Users {
        return wrapCall {
            userDataSource.getAllUsers(
                clientGravatar, includeCustomProfileFields
            )
        }.toUsers()
    }

    override suspend fun getOwnUser(): OwnerUser {
        return wrapCall {
            userDataSource.getOwnUser()
        }.toOwnerUser()

    }

    override suspend fun getUserById(
        userId: Int, clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): User {
        return wrapCall {
            userDataSource.getUserById(userId, clientGravatar, includeCustomProfileFields)
        }.toUser()
    }

    override suspend fun getUserByEmail(
        email: String, clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): User {
        return wrapCall {
            userDataSource.getUserByEmail(email, clientGravatar, includeCustomProfileFields)
        }.toUser()
    }

    override suspend fun updateUserById(
        id: Int, fullName: String, role: Int, profileData: List<ProfileData>
    ) {

        wrapCall {
            userDataSource.updateUserById(
                id,
                fullName,
                role,
                profileData.map { it.toProfileDataDto() })
        }
    }

    override suspend fun updateUserStatus(
        statusText: String,
        away: Boolean,
        emojiName: String,
        emojiCode: String,
        reactionType: String
    ) {
        wrapCall {
            userDataSource.updateUserStatus(
                statusText, away, emojiName, emojiCode, reactionType
            )
        }
    }

    override suspend fun createUser(email: String, password: String, fullName: String): CreateUser {
        return wrapCall {
            userDataSource.createUser(email, password, fullName)
        }.toCreateUser()
    }

    override suspend fun deactivateUserAccount(id: Int) {

        wrapCall {
            userDataSource.deactivateUserAccount(id)
        }
    }

    override suspend fun reactivateUserAccount(id: Int) {
        wrapCall {
            userDataSource.reactivateUserAccount(id)
        }
    }

    override suspend fun deactivateOwnUserAccount() {
        wrapCall {
            userDataSource.deactivateOwnUserAccount()
        }
    }

    override suspend fun setTypingStatus(
        op: String, to: String, type: String, topic: String
    ) {
        wrapCall {
            userDataSource.setTypingStatus(op, to, type, topic)
        }
    }

    override suspend fun getUserPresence(email: String): UserState {
        return wrapCall {
            userDataSource.getUserPresence(email)
        }.toUserState()
    }

    override suspend fun getRealmPresence(): UsersState {
        return wrapCall {
            userDataSource.getRealmPresence()
        }.toUsersState()
    }

    override suspend fun getAttachments(): UserAttachments {
        return wrapCall {
            userDataSource.getAttachments()
        }.toUserAttachments()
    }

    override suspend fun deleteAttachment(attachmentId: Int) {
        wrapCall {
            userDataSource.deleteAttachment(attachmentId)
        }
    }

    override suspend fun updateSettings(settings: Settings): UserSettings {
        return wrapCall {
            userDataSource.updateSettings(settings.toSettingsDto())
        }.toUserSettings()

    }

    override suspend fun getUserGroups(): UserGroups {
        return wrapCall {
            userDataSource.getUserGroups()
        }.toUserGroups()
    }

    override suspend fun createUserGroup(
        name: String, description: String, members: String
    ) {
        wrapCall {
            userDataSource.createUserGroup(name, description, members)
        }
    }

    override suspend fun updateUserGroup(
        userGroupId: Int, name: String, description: String
    ) {
        wrapCall {
            userDataSource.updateUserGroup(userGroupId, name, description)
        }
    }

    override suspend fun removeUserGroup(userGroupId: Int) {
        wrapCall {
            userDataSource.removeUserGroup(userGroupId)
        }
    }

    override suspend fun updateUserGroupMembers(
        id: Int, add: List<Int>, delete: List<Int>
    ) {
        wrapCall {
            userDataSource.updateUserGroupMembers(id, add, delete)
        }
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int, add: List<Int>, delete: List<Int>
    ): SubgroupsOfUserGroup {
        return wrapCall {
            userDataSource.updateUserGroupSubgroups(userGroupId, add, delete)
        }.toSubgroupsOfUserGroup()
    }

    override suspend fun getUserMembership(
        groupId: Int, userId: Int, directMemberOnly: Boolean
    ): UserMembershipState {
        return wrapCall {
            userDataSource.getUserMembership(groupId, userId, directMemberOnly)
        }.toUserMembershipState()
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int, directMemberOnly: Boolean
    ): UserGroupMemberships {
        return wrapCall {
            userDataSource.getUserGroupMemberships(groupId, directMemberOnly)
        }.toUserGroupMemberships()
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int, directSubgroupOnly: Boolean
    ): SubgroupsOfUserGroup {
        return wrapCall {
            userDataSource.getSubgroupsOfUserGroup(id, directSubgroupOnly)
        }.toSubgroupsOfUserGroup()
    }

    override suspend fun getAlertWords(): AlertWords {
        return wrapCall {
            userDataSource.getAlertWords()
        }.toAlertWords()
    }

    override suspend fun addAlertWords(alertWords: String): AlertWords {
        return wrapCall {
            userDataSource.addAlertWords(alertWords)
        }.toAlertWords()
    }

    override suspend fun removeAlertWords(alertWords: String): AlertWords {
        return wrapCall {
            userDataSource.removeAlertWords(alertWords)
        }.toAlertWords()
    }

    override suspend fun muteUser(mutedUserId: Int) {
        wrapCall {
            userDataSource.muteUser(mutedUserId)
        }
    }

    override suspend fun unMuteUser(mutedUserId: Int) {
        wrapCall {
            userDataSource.unmuteUser(mutedUserId)
        }
    }

    override suspend fun userLogin(userName: String, password: String): Boolean {
        return wrapCall { userDataSource.fetchApiKey(userName, password) }
            .takeIf { it.result == "success" }?.run {
                userDataStoreDataSource.putAuthenticationData(
                    apikey = apiKey ?: "",
                    email = email ?: ""
                )
                true
            } ?: false
    }

}