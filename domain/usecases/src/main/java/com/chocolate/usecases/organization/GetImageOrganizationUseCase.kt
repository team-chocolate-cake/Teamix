package com.chocolate.usecases.organization

import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class GetImageOrganizationUseCase @Inject constructor(
    private val organizationsRepository: ServerAndOrganizationsRepository
) {
    suspend operator fun invoke(): String {
        return organizationsRepository.getOrganizationImage()
    }
}