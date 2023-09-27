package com.chocolate.usecases.member

import com.chocolate.entities.entity.Member
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMembersExceptCurrentMemberUseCase @Inject constructor(
    private val getIdOfCurrentMemberUseCase: GetIdOfCurrentMemberUseCase,
    private val getMembersInOrganizationUseCase: GetMembersInOrganizationUseCase,
) {
    suspend operator fun invoke(): Flow<List<Member>> {
        val currentMemberId = getIdOfCurrentMemberUseCase()
        return getMembersInOrganizationUseCase().map {
            it.filter { member-> member.id != currentMemberId }
        }
    }
}