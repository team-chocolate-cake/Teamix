package com.chocolate.repository.mappers

import com.chocolate.entities.organization.Organization
import com.chocolate.repository.model.dto.organization.OrganizationDto

@JvmName("organizationDtoToOrganization")
fun OrganizationDto.toEntity(): Organization = Organization(
    name = name!!,
    imageUrl = imageUrl!!,
    invitationCode = invitationCode!!,
)

@JvmName("organizationToOrganizationDto")
fun Organization.toEntity(): OrganizationDto = OrganizationDto(
    name = name,
    imageUrl = imageUrl,
    invitationCode = invitationCode,
)