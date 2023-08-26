package com.chocolate.remote.data_source

import com.chocolate.entities.user.User
import com.chocolate.remote.api.UsersService
import com.chocolate.remote.wrapApiCall
import com.chocolate.repository.datastore.remote.UserRemoteDataSource
import com.chocolate.repository.model.dto.users.response.FetchApiKeyDto
import com.chocolate.repository.model.dto.users.response.StatusUserRemoteDto
import javax.inject.Inject

class UserRetrofitDataSource @Inject constructor(
    private val userService: UsersService
) : UserRemoteDataSource {

    override suspend fun getAllUsers() = wrapApiCall {
        userService.getAllUsers()
    }

    override suspend fun getOwnUser() = wrapApiCall {
        userService.getOwnUser()
    }

    override suspend fun getUserStatus(): StatusUserRemoteDto {
        return wrapApiCall { userService.getUserStatus() }
    }

    override suspend fun getUserById(
        userId: Int,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ) = wrapApiCall {
        userService.getUserById(userId, clientGravatar, includeCustomProfileFields)
    }

    override suspend fun getUserByEmail(
        email: String,
        clientGravatar: Boolean,
        includeCustomProfileFields: Boolean
    ) = wrapApiCall {
        userService.getUserByEmail(email, clientGravatar, includeCustomProfileFields)
    }

    override suspend fun updateUserById(
        id: Int,
        fullName: String?,
        role: Int?,
    ) = wrapApiCall {
        userService.updateUserById(id, fullName, role)
    }

    override suspend fun deactivateUserAccount(id: Int) = wrapApiCall {
        userService.deactivateUser(id)
    }

    override suspend fun reactivateUserAccount(id: Int) = wrapApiCall {
        userService.reactivateUser(id)
    }

    override suspend fun deactivateOwnUserAccount() = wrapApiCall {
        userService.deactivateOwnUser()
    }

    override suspend fun getUserPresence(email: String) = wrapApiCall {
        userService.getUserPresence(email)
    }

    override suspend fun getRealmPresence() = wrapApiCall {
        userService.getRealmPresence()
    }

    override suspend fun getAttachments() = wrapApiCall {
        userService.getAttachments()
    }

    override suspend fun deleteAttachment(attachmentId: Int) = wrapApiCall {
        userService.deleteAttachment(attachmentId)
    }

    override suspend fun updateSettings(user: User) = wrapApiCall {
        userService.updateSettings(user.fullName, user.email)
    }

    override suspend fun getUserGroups() = wrapApiCall {
        userService.getUserGroups()
    }

    override suspend fun createUserGroup(
        name: String,
        description: String,
        members: String
    ) = wrapApiCall {
        userService.createUserGroup(name, description, members)
    }

    override suspend fun updateUserGroup(
        userGroupId: Int,
        name: String,
        description: String
    ) = wrapApiCall {
        userService.updateUserGroup(userGroupId, name, description)
    }

    override suspend fun removeUserGroup(userGroupId: Int) = wrapApiCall {
        userService.removeUserGroup(userGroupId)
    }

    override suspend fun updateUserGroupMembers(
        id: Int,
        add: List<Int>,
        delete: List<Int>
    ) = wrapApiCall {
        userService.updateUserGroupMembers(id, add, delete)
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int,
        add: List<Int>?,
        delete: List<Int>?
    ) = wrapApiCall {
        userService.updateUserGroupSubgroups(userGroupId, add, delete)
    }

    override suspend fun getUserMembership(
        groupId: Int,
        userId: Int,
        directMemberOnly: Boolean
    ) = wrapApiCall {
        userService.getUserMembership(groupId, userId, directMemberOnly)
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int,
        directMemberOnly: Boolean
    ) = wrapApiCall {
        userService.getUserGroupMemberships(groupId, directMemberOnly)
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int,
        directSubgroupOnly: Boolean
    ) = wrapApiCall {
        userService.getSubgroupsOfUserGroup(id, directSubgroupOnly)
    }

    override suspend fun muteUser(mutedUserId: Int) = wrapApiCall {
        userService.muteUser(mutedUserId)
    }

    override suspend fun unMuteUser(mutedUserId: Int) = wrapApiCall {
        userService.unMuteUser(mutedUserId)
    }

    override suspend fun fetchApiKey(userName: String, password: String): FetchApiKeyDto {
        return wrapApiCall {
            userService.fetchApiKey(userName, password)
        }
    }
}