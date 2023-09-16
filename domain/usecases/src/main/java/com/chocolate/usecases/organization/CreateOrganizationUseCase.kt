package com.chocolate.usecases.organization

import com.chocolate.entities.organization.Organization
import repositories.OrganizationsRepository
import javax.inject.Inject

class CreateOrganizationUseCase @Inject constructor(
    private val organizationsRepository: OrganizationsRepository,
) {

    suspend operator fun invoke(organization: Organization) {
        organizationsRepository.createOrganization(organization)
    }

}