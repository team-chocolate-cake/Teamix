package com.chocolate.usecases.organization

import com.chocolate.entities.member.Member
import com.chocolate.entities.organization.Organization
import repositories.MemberRepository
import repositories.OrganizationsRepository
import javax.inject.Inject

class CreateOrganizationUseCase @Inject constructor(
    private val organizationsRepository: OrganizationsRepository,
    private val memberRepository: MemberRepository
) {

    suspend operator fun invoke(organization: Organization, owner: Member) {
        organizationsRepository.createOrganization(organization).also {
            memberRepository.createMember(owner)
        }
    }

}