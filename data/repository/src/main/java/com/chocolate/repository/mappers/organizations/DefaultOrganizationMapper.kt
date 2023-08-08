package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.DefaultOrganization
import com.chocolate.repository.dto.remote.server_and_organizations.response.DefaultOrganizationDto

fun DefaultOrganizationDto.toEntity(): DefaultOrganization {
    return DefaultOrganization(
        id = this.id ?: 0
    )
}