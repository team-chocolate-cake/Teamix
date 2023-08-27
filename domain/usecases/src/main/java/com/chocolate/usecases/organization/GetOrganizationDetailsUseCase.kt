package com.chocolate.usecases.organization

import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class ManageOrganizationDetailsUseCase @Inject constructor(
    private val organizationsRepository: ServerAndOrganizationsRepository,

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