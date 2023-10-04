package com.chocolate.usecases.usecase.member

import com.chocolate.entities.entity.Member
import com.chocolate.usecases.usecase.organization.ManageOrganizationUseCase
import kotlinx.coroutines.flow.Flow
import com.chocolate.usecases.repositories.MemberRepository
import javax.inject.Inject

class GetMembersInOrganizationUseCase @Inject constructor(
    private val repository: MemberRepository,
    private val manageOrganizationDetailsUseCase: ManageOrganizationUseCase
) {
    suspend operator fun invoke(): Flow<List<Member>> {
        val currentOrganizationName = manageOrganizationDetailsUseCase.getOrganizationName()
        return repository.getMembersInOrganizationByOrganizationName(currentOrganizationName)
    }

}