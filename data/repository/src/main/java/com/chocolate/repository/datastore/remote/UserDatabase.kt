package com.chocolate.repository.datastore.remote

import com.chocolate.entities.user.User

interface UserDatabase {
    suspend fun setUsers(user: User)
}