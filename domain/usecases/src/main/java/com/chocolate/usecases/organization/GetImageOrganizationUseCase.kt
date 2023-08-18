package com.chocolate.usecases.organization

import com.chocolate.entities.server_and_organizations.ServerSettings
import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class GetImageOrganizationUseCase @Inject constructor(
    private val organizationsRepository: ServerAndOrganizationsRepository
) {
    suspend operator fun invoke(): ServerSettings {
        return organizationsRepository.getServiceSettings()
    }
}