package com.chocolate.repository.datastore.remote

import com.chocolate.entities.user.User

interface TaskRemoteDataSource {
    suspend fun setUsers(user: User)
    suspend fun getAllUser(): List<User?>
}