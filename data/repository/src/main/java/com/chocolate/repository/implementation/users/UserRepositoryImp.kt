package com.chocolate.repository.implementation.users

import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.UsersDataSource
import repositories.users.UsersRepositories
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(userDataSource: UsersDataSource) : UsersRepositories,
    BaseRepository() {
    override suspend fun getAllUsers() {

    }

    override suspend fun getOwnUser() {

    }

    override suspend fun getUserById() {

    }

    override suspend fun getUserByEmail() {

    }

    override suspend fun updateUserById() {

    }

    override suspend fun updateUserStatus() {

    }

    override suspend fun createUser() {

    }

    override suspend fun deactivateUser() {

    }

    override suspend fun reactivateUser() {

    }

    override suspend fun deactivateOwnUser() {

    }

    override suspend fun setTypingStatus() {

    }

    override suspend fun getUserPresence() {
    }

    override suspend fun getRealmPresence() {
    }

    override suspend fun getAttachments() {
    }

    override suspend fun deleteAttachment() {
    }

    override suspend fun updateSettings() {
    }

    override suspend fun getUserGroups() {
    }

    override suspend fun createUserGroup() {
    }

    override suspend fun updateUserGroup() {
    }

    override suspend fun removeUserGroup() {
    }

    override suspend fun updateUserGroupMembers() {
    }

    override suspend fun createUserGroupSubgroups() {
    }

    override suspend fun getUserMembership() {
    }

    override suspend fun getGroupMembers() {
    }

    override suspend fun getSubgroups() {
    }

    override suspend fun muteUser() {
    }

    override suspend fun unmuteUser() {
    }

    override suspend fun getAlertWords() {
    }

    override suspend fun addAlertWords() {
    }

    override suspend fun removeAlertWords() {
    }
}