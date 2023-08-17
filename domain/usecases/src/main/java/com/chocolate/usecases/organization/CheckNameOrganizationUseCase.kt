package com.chocolate.usecases.organization

import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class SaveNameOrganizationUseCase @Inject constructor(
    private val organizationsRepository: ServerAndOrganizationsRepository
){
    suspend operator fun invoke(nameOrganization: String): Boolean{
        return nameOrganization.takeIf { it.isNotBlank() }?.run {
            organizationsRepository.saveNameOrganizations(this)
            true
        }?: false
    }
}