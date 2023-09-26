package com.chocolate.usecases.member

import com.chocolate.entities.member.Member
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.MemberRepository
import javax.inject.Inject

class GetMembersInOrganizationUseCase @Inject constructor(
    private val repository: MemberRepository,
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase
) {
    suspend operator fun invoke(): Flow<List<Member>> {
        val currentOrganizationName = manageOrganizationDetailsUseCase.getOrganizationName()
        return repository.getMembersInOrganizationByOrganizationName(currentOrganizationName)
    }

    suspend fun searchUser(username: String): Flow<List<Member>> {
        return invoke().let { flow ->
            flow.map { members -> members.filter { it.name.contains(username, true) } }
        }
    }
}