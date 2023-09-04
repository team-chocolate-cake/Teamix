package com.chocolate.usecases.organization

import repositories.OrganizationsRepository
import javax.inject.Inject

class ManageOrganizationDetailsUseCase @Inject constructor(
    private val organizationsRepository: OrganizationsRepository,

    ) {
    suspend fun saveOrganizationName(nameOrganization: String): Boolean {
        return nameOrganization.takeIf { it.isNotBlank() }?.run {
            organizationsRepository.saveOrganizationName(this)
            true
        } ?: false
    }

    suspend fun getOrganizationName(): String {
        return organizationsRepository.getOrganizationName()
    }

    suspend fun getOrganizationImage(): String {
        return organizationsRepository.getOrganizationImage()
    }
}