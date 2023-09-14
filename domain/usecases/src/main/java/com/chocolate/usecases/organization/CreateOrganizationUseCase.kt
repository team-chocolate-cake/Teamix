package com.chocolate.usecases.organization

import com.chocolate.entities.organization.Organization
import repositories.OrganizationsRepository
import javax.inject.Inject

class CreateOrganizationUseCase @Inject constructor(
    private val repository: OrganizationsRepository
) {

    suspend operator fun invoke(name: String, imageUri: String) {
        repository.createOrganization(Organization(name,  imageUri, "fake invitation Code"))
    }

}