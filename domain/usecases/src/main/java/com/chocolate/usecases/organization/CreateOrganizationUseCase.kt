package com.chocolate.usecases.organization

import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.exceptions.InvalidOrganizationImageUrl
import com.chocolate.entities.exceptions.OrganizationAlreadyExistException
import com.chocolate.entities.exceptions.OrganizationNameIsSoLongException
import com.chocolate.entities.member.Member
import com.chocolate.entities.organization.Organization
import com.chocolate.usecases.member.CreateMemberUseCase
import repositories.OrganizationRepository
import javax.inject.Inject

class CreateOrganizationUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository,
    private val createMemberUseCase: CreateMemberUseCase
) {
    suspend operator fun invoke(organization: Organization) {
        organizationRepository.createOrganization(organization)
    }

    suspend fun createOrganizationAndOwner(
        organization: Organization,
        owner: Member,
        confirmPassword: String
    ) {
        createMemberUseCase.validateMemberInformation(owner, confirmPassword).also {
            organizationRepository.createOrganization(organization)
                .also {
                    createMemberUseCase.createMember(owner)
                }
        }
    }

    suspend fun validateOrganizationData(organizationName: String, organizationImageUri: String) {
        if (organizationName.isBlank()) {
            throw EmptyOrganizationNameException
        } else if (organizationImageUri.isBlank()) {
            throw InvalidOrganizationImageUrl
        } else if (isOrganizationExist(organizationName)) {
            throw OrganizationAlreadyExistException
        } else if (organizationName.length > 30) {
            throw OrganizationNameIsSoLongException
        }
    }

    private suspend fun isOrganizationExist(organizationName: String): Boolean {
        return organizationRepository.getOrganizationByName(organizationName) != null
    }

}