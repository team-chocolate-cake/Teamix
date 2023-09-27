package com.chocolate.usecases.organization

import com.chocolate.entities.utils.EmptyOrganizationNameException
import com.chocolate.entities.utils.OrganizationNotFoundException
import repositories.OrganizationRepository
import javax.inject.Inject

class ManageOrganizationDetailsUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository,

    ) {
    suspend fun saveOrganizationName(organizationName: String): Boolean {
        organizationRepository.saveOrganizationName(organizationName)
        return true
    }

    suspend fun getOrganizationName(): String {
        return organizationRepository.getOrganizationName()
    }

    suspend fun getOrganizationImage(): String {
        return organizationRepository.getOrganizationImage()
    }

    suspend fun organizationSignIn(organizationName: String): String {
        if (organizationName.isBlank()) throw EmptyOrganizationNameException
        val organization = organizationRepository.getOrganizationByName(organizationName)
            ?: throw OrganizationNotFoundException
        return organization.name
    }
}