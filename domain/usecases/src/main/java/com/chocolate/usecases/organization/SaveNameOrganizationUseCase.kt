package com.chocolate.usecases.organization

import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class SaveNameOrganizationUseCase @Inject constructor(
    private val organizationsRepository: ServerAndOrganizationsRepository
){
    suspend operator fun invoke(nameOrganization: String){
        organizationsRepository.saveNameOrganizations(nameOrganization)
    }
}