package com.chocolate.usecases.member

import com.chocolate.entities.member.Member
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.MemberRepository
import javax.inject.Inject

class GetMembersInOrganizationUseCase @Inject constructor(
    private val repository: MemberRepository
) {
    suspend operator fun invoke(): Flow<List<Member>> {
        return repository.getMembersInCurrentOrganization()
    }

    suspend fun searchUser(username: String): Flow<List<Member>> {
        return invoke().let { flow ->
            flow.map { members -> members.filter { it.name.contains(username, true) } }
        }
    }
}