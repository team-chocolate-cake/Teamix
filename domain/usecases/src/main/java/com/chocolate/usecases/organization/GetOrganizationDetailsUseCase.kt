package com.chocolate.usecases.organization

import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.exceptions.OrganizationNotFoundException
import repositories.OrganizationsRepository
import javax.inject.Inject

class ManageOrganizationDetailsUseCase @Inject constructor(
    private val organizationsRepository: OrganizationsRepository,

    ) {
    suspend fun saveOrganizationName(organizationName: String) {
        organizationsRepository.saveOrganizationName(organizationName)
    }

/*    suspend fun getOrganizationName(): String {
        return organizationsRepository.getOrganizationName()
    }

    suspend fun getOrganizationImage(): String {
        return organizationsRepository.getOrganizationImage()
    }*/

    suspend fun organizationSignIn(organizationName: String): String {
        if (organizationName.isBlank()) throw EmptyOrganizationNameException
        val organization = organizationsRepository.getOrganizationByName(organizationName)
            ?: throw OrganizationNotFoundException
        return organization.name
    }

}