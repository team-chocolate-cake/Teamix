package com.chocolate.repository.datastore.remote

import com.chocolate.entities.member.Member

interface TaskRemoteDataSource {
    suspend fun setUsers(member: Member)
    suspend fun getAllUser(): List<Member?>
}