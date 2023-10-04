package com.chocolate.usecases.repositories

import com.chocolate.entities.entity.Member
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    fun checkIfUserUsedAppOrNot(): Flow<Boolean>

    suspend fun setUserUsedAppForFirstTime(isComplete: Boolean)

    suspend fun getMembersInOrganizationByOrganizationName(organizationName: String): Flow<List<Member>>

    suspend fun getMemberInOrganizationByEmail(email: String): Member

    suspend fun loginMember(email: String, password: String)

    suspend fun isMemberLoggedIn(): Flow<Boolean>

    suspend fun logoutMember()

    suspend fun getCurrentMember(): Member

    suspend fun createMember(member: Member): Member

    suspend fun updateMember(member: Member)

    suspend fun updateMemberPicture(imageUri: String)

    suspend fun getIdOfCurrentMember(): String
}