package com.chocolate.repository.repository

import com.chocolate.entities.task.Task
import com.chocolate.entities.uills.Empty
import com.chocolate.entities.user.Attachment
import com.chocolate.entities.user.User
import com.chocolate.repository.datastore.local.LocalDataSource
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.TaskRemoteDataSource
import com.chocolate.repository.datastore.remote.UserRemoteDataSource
import com.chocolate.repository.mappers.task.toEntity
import com.chocolate.repository.mappers.task.toRemoteDto
import com.chocolate.repository.mappers.users.toEntity
import com.chocolate.repository.mappers.users.toLocalDto
import com.chocolate.repository.mappers.users.toRemoteDto
import com.chocolate.repository.utils.SUCCESS
import kotlinx.coroutines.flow.Flow
import repositories.UsersRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val teamixLocalDataSource: LocalDataSource,
    private val taskRemoteDataSource: TaskRemoteDataSource
) : UsersRepository {
    override suspend fun setUserUsedAppForFirstTime(isComplete: Boolean) {
        preferencesDataSource.setUserUsedAppForFirstTime(isComplete)
    }

    override suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean> {
        return preferencesDataSource.checkIfUserUsedAppOrNot()
    }

    override suspend fun getAllUsers(): List<User> {
        return userRemoteDataSource.getAllUsers().memberDto.toEntity()
    }

    override suspend fun getRemoteCurrentUser(): User {
        return userRemoteDataSource.getOwnUser().toEntity()
    }

    override suspend fun getUserById(
        userId: Int, clientGravatar: Boolean, includeCustomProfileFields: Boolean
    ): User {
        return userRemoteDataSource.getUserById(userId, clientGravatar, includeCustomProfileFields)
            .toEntity()
    }

    override suspend fun getUserByEmail(
        email: String
    ): User {
        return userRemoteDataSource.getUserByEmail(email).toEntity()
    }

    override suspend fun updateUserById(
        id: Int, fullName: String, role: Int
    ) {
        userRemoteDataSource.updateUserById(
            id,
            fullName,
            role,
        )
    }

    override suspend fun deactivateUserAccount(id: Int) {
        userRemoteDataSource.deactivateUserAccount(id)
    }

    override suspend fun reactivateUserAccount(id: Int) {
        userRemoteDataSource.reactivateUserAccount(id)
    }

    override suspend fun deactivateOwnUserAccount() {
        userRemoteDataSource.deactivateOwnUserAccount()
    }

    override suspend fun getUserPresence(email: String): String {
        return userRemoteDataSource.getUserPresence(email).presenceDto?.aggregatedDto?.status
            ?: String.Empty
    }

    override suspend fun getRealmPresence(): String {
        return userRemoteDataSource.getRealmPresence().presencesDto?.iagoZulipComDto?.aggregatedDto?.status
            ?: String.Empty
    }

    override suspend fun getAttachments(): List<Attachment> {
        return userRemoteDataSource.getAttachments().attachmentDto.toEntity()
    }

    override suspend fun deleteAttachment(attachmentId: Int) {
        userRemoteDataSource.deleteAttachment(attachmentId)
    }

    override suspend fun updateSettings(user: User) {
        userRemoteDataSource.updateSettings(user)
    }

    override suspend fun createUserGroup(
        name: String, description: String, members: String
    ) {
        userRemoteDataSource.createUserGroup(name, description, members)
    }

    override suspend fun updateUserGroup(
        userGroupId: Int, name: String, description: String
    ) {
        userRemoteDataSource.updateUserGroup(userGroupId, name, description)
    }

    override suspend fun removeUserGroup(userGroupId: Int) {
        userRemoteDataSource.removeUserGroup(userGroupId)
    }

    override suspend fun updateUserGroupMembers(
        id: Int, add: List<Int>, delete: List<Int>
    ) {
        userRemoteDataSource.updateUserGroupMembers(id, add, delete)
    }

    override suspend fun updateUserGroupSubgroups(
        userGroupId: Int, add: List<Int>, delete: List<Int>
    ): List<Int> {
        return userRemoteDataSource.updateUserGroupSubgroups(userGroupId, add, delete).subgroups
            ?: emptyList()
    }

    override suspend fun getUserMembership(
        groupId: Int, userId: Int, directMemberOnly: Boolean
    ): Boolean {
        return userRemoteDataSource.getUserMembership(
            groupId,
            userId,
            directMemberOnly
        ).isUserGroupMember
            ?: false
    }

    override suspend fun getUserGroupMemberships(
        groupId: Int, directMemberOnly: Boolean
    ): List<Int> {
        return userRemoteDataSource.getUserGroupMemberships(groupId, directMemberOnly).members
            ?: emptyList()
    }

    override suspend fun getSubgroupsOfUserGroup(
        id: Int, directSubgroupOnly: Boolean
    ): List<Int> {
        return userRemoteDataSource.getSubgroupsOfUserGroup(id, directSubgroupOnly).subgroups
            ?: emptyList()
    }

    override suspend fun muteUser(mutedUserId: Int) {
        userRemoteDataSource.muteUser(mutedUserId)
    }

    override suspend fun unMuteUser(mutedUserId: Int) {
        userRemoteDataSource.unMuteUser(mutedUserId)
    }

    override suspend fun userLogin(userName: String, password: String): Boolean {
        return userRemoteDataSource.fetchApiKey(userName, password)
            .takeIf {
                it.result == SUCCESS
            }?.run {
                preferencesDataSource.setAuthenticationData(
                    apikey = apiKey ?: String.Empty,
                    email = email ?: String.Empty
                )
                val currentUser = getRemoteCurrentUser().toRemoteDto()
                taskRemoteDataSource.setUsers(currentUser)
                true
            } ?: false
    }

    override suspend fun setUserLoginState(isComplete: Boolean) {
        preferencesDataSource.setUserLoginState(isComplete)
    }

    override suspend fun getUserLoginState(): Flow<Boolean> {
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
        teamixLocalDataSource.upsertUserData(user.toLocalDto())
    }

    override suspend fun getLocalCurrentUser(): User? {
        return teamixLocalDataSource.getCurrentUserData()?.toEntity()
    }

    override suspend fun getCurrentUser(): User {
        return getLocalCurrentUser()
            .takeIf { it != null }
            ?: getRemoteCurrentUser()
                .also { upsertCurrentUser(it.email) }
    }

    override suspend fun setTeamTask(task:Task) {
        taskRemoteDataSource.setTeamTask(task.toRemoteDto())
    }

    override suspend fun getTeamTask(): List<Task?> {
        return taskRemoteDataSource.getTeamTasks().toEntity()
    }
}