package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.Linkifier
import com.chocolate.repository.model.dto.server_and_organizations.response.LinkifierDto

fun LinkifierDto.toLinkifier() : Linkifier = Linkifier(
    id = id?:-1,
    pattern = pattern.orEmpty(),
    urlTemplate = urlTemplate.orEmpty()
)
fun List<LinkifierDto>?.toLinkifiers() : List<Linkifier> = this?.map { it.toLinkifier() } ?: emptyList()
