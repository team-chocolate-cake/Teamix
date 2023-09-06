package com.chocolate.repository.datastore.remote

import com.chocolate.entities.user.Member
import com.chocolate.entities.user.UserRole
import kotlinx.coroutines.flow.Flow

interface MemberDataSource {
    suspend fun getMemberById(id: String): Member?
    suspend fun getMembersInChannelByChannelId(id: String): Flow<List<Member>>
    suspend fun getMembersInOrganizationByOrganizationId(id: String): Flow<List<Member>>
    suspend fun deactivateMember(memberId: String)
    suspend fun updateMember(member: Member)
    suspend fun changeRole(memberId: String, newRole: UserRole)
    suspend fun addMembersInChannel(members: List<String>, channelId: String)
}