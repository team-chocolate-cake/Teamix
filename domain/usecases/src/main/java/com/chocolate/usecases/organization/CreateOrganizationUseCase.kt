package com.chocolate.usecases.organization

import com.chocolate.entities.Organization
import repositories.OrganizationRepository
import javax.inject.Inject

class CreateOrganizationUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository,
) {

    suspend operator fun invoke(organization: Organization) {
        organizationRepository.createOrganization(organization)
    }

}