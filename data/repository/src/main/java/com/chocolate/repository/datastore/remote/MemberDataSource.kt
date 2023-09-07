package com.chocolate.repository.datastore.remote

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.user.Member
import com.chocolate.entities.user.UserRole
import kotlinx.coroutines.flow.Flow

interface MemberDataSource {
    suspend fun getMemberInOrganizationById(memberId: String, organizationName: String): Member?
    suspend fun getMembersInChannelByChannelId(organizationName: String, channelId: String): Flow<List<Member>>
    suspend fun getMembersInOrganizationByOrganizationName(organizationName: String): Flow<List<Member>?>
    suspend fun getChannelsInOrganizationByOrganizationName(organizationName: String): Flow<List<Channel>?>
    suspend fun deactivateMember(organizationName: String, memberId: String)
    suspend fun updateMember(organizationName: String, member: Member)
    suspend fun changeRole(organizationName: String, memberId: String, newRole: UserRole)
    suspend fun addMembersInChannel(organizationName: String, members: List<String>, channelId: String)
    suspend fun getMemberInOrganizationByEmail(organizationName: String, email: String): Member?
}