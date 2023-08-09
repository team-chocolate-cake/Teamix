package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.Linkifier
import com.chocolate.entities.server_and_organizations.Linkifiers
import com.chocolate.repository.model.dto.server_and_organizations.response.LinkifiersDto

fun LinkifiersDto.toLinkifiers(): Linkifiers {
    val linkifiers = linkifierDtos?.map { linkifierDto ->
        Linkifier(
            id = linkifierDto.id ?: 0,
            pattern = linkifierDto.pattern ?: "",
            urlTemplate = linkifierDto.urlTemplate ?: ""
        )
    } ?: emptyList()

    return Linkifiers(linkifiers = linkifiers)
}
