package com.chocolate.repository.implementation.users

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
import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.mapper.users.response.toCreateUser
import com.chocolate.repository.mapper.users.response.toOwnerUser
import com.chocolate.repository.mapper.users.request.toProfileDataDto
import com.chocolate.repository.mapper.users.request.toSettingsRequestDto
import com.chocolate.repository.mapper.users.response.toAlertWords
import com.chocolate.repository.mapper.users.response.toSubgroupsOfUserGroup
import com.chocolate.repository.mapper.users.response.toUser
import com.chocolate.repository.mapper.users.response.toUserAttachments
import com.chocolate.repository.mapper.users.response.toUserGroupMemberships
import com.chocolate.repository.mapper.users.response.toUserGroups
import com.chocolate.repository.mapper.users.response.toUserMembershipState
import com.chocolate.repository.mapper.users.response.toUserSettings
import com.chocolate.repository.mapper.users.response.toUserState
import com.chocolate.repository.mapper.users.response.toUsers
import com.chocolate.repository.mapper.users.response.toUsersState
import com.chocolate.repository.service.UsersDataSource
import repositories.users.UsersRepositories
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userDataSource: UsersDataSource
) : UsersRepositories, BaseRepository() {
    override suspend fun getAllUsers(
        clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): Users {
        return wrapApiCall {
            userDataSource.getAllUsers(
                clientGravatar, includeCustomProfileFields
            )
        }.toUsers()
    }

    override suspend fun getOwnUser(): OwnerUser {
        return wrapApiCall {
            userDataSource.getOwnUser()
        }.toOwnerUser()

    }

    override suspend fun getUserById(
        userId: Int, clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): User {
        return wrapApiCall {
            userDataSource.getUserById(userId, clientGravatar, includeCustomProfileFields)
        }.toUser()
    }

    override suspend fun getUserByEmail(
        email: String, clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): User {
        return wrapApiCall {
            userDataSource.getUserByEmail(email, clientGravatar, includeCustomProfileFields)
        }.toUser()
    }

    override suspend fun updateUserById(
        id: Int, fullName: String, role: Int, profileData: List<ProfileData>
    ) {

        wrapApiCall {
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
        wrapApiCall {
            userDataSource.updateUserStatus(
                statusText, away, emojiName, emojiCode, reactionType
            )
        }
    }

    override suspend fun createUser(email: String, password: String, fullName: String): CreateUser {
        return wrapApiCall {
            userDataSource.createUser(email, password, fullName)
        }.toCreateUser()
    }

    override suspend fun deactivateUserAccount(id: Int) {

        wrapApiCall {
            userDataSource.deactivateUserAccount(id)
        }
    }

    override suspend fun reactivateUserAccount(id: Int) {
        wrapApiCall {
            userDataSource.reactivateUserAccount(id)
        }
    }

    override suspend fun deactivateOwnUserAccount() {
        wrapApiCall {
            userDataSource.deactivateOwnUserAccount()
        }
    }

    override suspend fun setTypingStatus(
        op: String, to: String, type: String, topic: String
    ) {
        wrapApiCall {
            userDataSource.setTypingStatus(op, to, type, topic)
        }
    }

    override suspend fun getUserPresence(email: String): UserState {
        return wrapApiCall {
            userDataSource.getUserPresence(email)
        }.toUserState()
    }

    override suspend fun getRealmPresence(): UsersState {
        return wrapApiCall {
            userDataSource.getRealmPresence()
        }.toUsersState()
    }

    override suspend fun getAttachments(): UserAttachments {
        return wrapApiCall {
            userDataSource.getAttachments()
        }.toUserAttachments()
    }

    override suspend fun deleteAttachment(attachmentId: Int) {
        wrapApiCall {
            userDataSource.deleteAttachment(attachmentId)
        }
    }

    override suspend fun updateSettings(settings: SettingsRequest): UserSettings {
        return wrapApiCall {
            userDataSource.updateSettings(settings.toSettingsRequestDto())
        }.toUserSettings()

    }

    override suspend fun getUserGroups(): UserGroups {
        return wrapApiCall {
            userDataSource.getUserGroups()
        }.toUserGroups()
    }

    override suspend fun createUserGroup(
        name: String, description: String, members: String
    ) {
        wrapApiCall {
            userDataSource.createUserGroup(name, description, members)
        }
    }

    override suspend fun updateUserGroup(
        userGroupId: Int, name: String, description: String
    ) {
        wrapApiCall {
            userDataSource.updateUserGroup(userGroupId, name, description)
        }
    }

    override suspend fun removeUserGroup(userGroupId: Int) {
        wrapApiCall {
            userDataSource.removeUserGroup(userGroupId)
        }
    }

    override suspend fun updateUserGroupMembers(
        id: Int, add: List<Int>, delete: List<Int>
    ) {
        wrapApiCall {
            userDataSource.updateUserGroupMembers(id, add, delete)
        }
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int, add: List<Int>, delete: List<Int>
    ): SubgroupsOfUserGroup {
        return wrapApiCall {
            userDataSource.updateUserGroupSubgroups(userGroupId, add, delete)
        }.toSubgroupsOfUserGroup()
    }

    override suspend fun getUserMembership(
        groupId: Int, userId: Int, directMemberOnly: Boolean
    ): UserMembershipState {
        return wrapApiCall {
            userDataSource.getUserMembership(groupId, userId, directMemberOnly)
        }.toUserMembershipState()
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int, directMemberOnly: Boolean
    ): UserGroupMemberships {
        return wrapApiCall {
            userDataSource.getUserGroupMemberships(groupId, directMemberOnly)
        }.toUserGroupMemberships()
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int, directSubgroupOnly: Boolean
    ): SubgroupsOfUserGroup {
        return wrapApiCall {
            userDataSource.getSubgroupsOfUserGroup(id, directSubgroupOnly)
        }.toSubgroupsOfUserGroup()
    }

    override suspend fun getAlertWords(): AlertWords {
        return wrapApiCall {
            userDataSource.getAlertWords()
        }.toAlertWords()
    }

    override suspend fun addAlertWords(alertWords: String): AlertWords {
        return wrapApiCall {
            userDataSource.addAlertWords(alertWords)
        }.toAlertWords()
    }

    override suspend fun removeAlertWords(alertWords: String): AlertWords {
        return wrapApiCall {
            userDataSource.removeAlertWords(alertWords)
        }.toAlertWords()
    }

    override suspend fun muteUser(mutedUserId: Int) {
        wrapApiCall {
            userDataSource.muteUser(mutedUserId)
        }
    }

    override suspend fun unMuteUser(mutedUserId: Int) {
        wrapApiCall {
            userDataSource.unmuteUser(mutedUserId)
        }
    }

}