package com.chocolate.repository.implementation.users

import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.remote.UsersDataSource
import repositories.users.UsersRepositories
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(userDataSource: UsersDataSource) : UsersRepositories,
    BaseRepository() {
    override suspend fun getAllUsers() {
        TODO("Not yet implemented")
    }

    override suspend fun getOwnUser() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByEmail() {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserById() {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserStatus() {
        TODO("Not yet implemented")
    }

    override suspend fun createUser() {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateUser() {
        TODO("Not yet implemented")
    }

    override suspend fun reactivateUser() {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateOwnUser() {
        TODO("Not yet implemented")
    }

    override suspend fun setTypingStatus() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPresence() {
        TODO("Not yet implemented")
    }

    override suspend fun getRealmPresence() {
        TODO("Not yet implemented")
    }

    override suspend fun getAttachments() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAttachment() {
        TODO("Not yet implemented")
    }

    override suspend fun updateSettings() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserGroups() {
        TODO("Not yet implemented")
    }

    override suspend fun createUserGroup() {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroup() {
        TODO("Not yet implemented")
    }

    override suspend fun removeUserGroup() {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserGroupMembers() {
        TODO("Not yet implemented")
    }

    override suspend fun createUserGroupSubgroups() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserMembership() {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupMembers() {
        TODO("Not yet implemented")
    }

    override suspend fun getSubgroups() {
        TODO("Not yet implemented")
    }

    override suspend fun muteUser() {
        TODO("Not yet implemented")
    }

    override suspend fun unmuteUser() {
        TODO("Not yet implemented")
    }

    override suspend fun getAlertWords() {
        TODO("Not yet implemented")
    }

    override suspend fun addAlertWords() {
        TODO("Not yet implemented")
    }

    override suspend fun removeAlertWords() {
        TODO("Not yet implemented")
    }

    override suspend fun addOrganizations(nameOrganizations: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getOrganizations(): String? {
        TODO("Not yet implemented")
    }

}