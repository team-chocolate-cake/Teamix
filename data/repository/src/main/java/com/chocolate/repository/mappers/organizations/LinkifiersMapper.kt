package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.LinkifierEntity
import com.chocolate.entities.server_and_organizations.LinkifiersEntity
import com.chocolate.repository.dto.remote.server_and_organizations.response.LinkifiersDto

fun LinkifiersDto.toEntity(): LinkifiersEntity {
    val linkifiersEntity = linkifiers?.map { linkifierDto ->
        LinkifierEntity(
            id = linkifierDto.id ?: 0,
            pattern = linkifierDto.pattern ?: "",
            urlTemplate = linkifierDto.urlTemplate ?: ""
        )
    } ?: emptyList()

    return LinkifiersEntity(linkifiers = linkifiersEntity)
}
