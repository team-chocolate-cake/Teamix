package repositories.users

interface UsersRepositories {

    suspend fun getAllUsers()
    suspend fun getOwnUser()
    suspend fun getUserById()
    suspend fun getUserByEmail()
    suspend fun updateUserById()
    suspend fun updateUserStatus()
    suspend fun createUser()
    suspend fun deactivateUser()
    suspend fun reactivateUser()
    suspend fun deactivateOwnUser()
    suspend fun setTypingStatus()
    suspend fun getUserPresence()
    suspend fun getRealmPresence()
    suspend fun getAttachments()
    suspend fun deleteAttachment()
    suspend fun updateSettings()
    suspend fun getUserGroups()
    suspend fun createUserGroup()
    suspend fun updateUserGroup()
    suspend fun removeUserGroup()
    suspend fun updateUserGroupMembers()
    suspend fun createUserGroupSubgroups()
    suspend fun getUserMembership()
    suspend fun getGroupMembers()
    suspend fun getSubgroups()
    suspend fun muteUser()
    suspend fun unmuteUser()
    suspend fun getAlertWords()
    suspend fun addAlertWords()
    suspend fun removeAlertWords()
    suspend fun addOrganizations(nameOrganizations: String)
    suspend fun getOrganizations(): String?


}