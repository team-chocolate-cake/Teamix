package com.chocolate.repository.repository

import com.chocolate.entities.user.User

interface UserDatabase {
    suspend fun setUsers(user: User)
    suspend fun getAllUser(): List<User?>
}