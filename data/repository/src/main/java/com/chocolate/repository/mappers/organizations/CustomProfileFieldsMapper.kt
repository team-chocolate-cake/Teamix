package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.CustomFieldEntity
import com.chocolate.entities.server_and_organizations.CustomProfileFields
import com.chocolate.repository.dto.remote.server_and_organizations.response.CustomProfileFieldsDto

fun CustomProfileFieldsDto.toEntity(): CustomProfileFields {
    val customFieldsEntity = customFields?.map { customFieldDto ->
        CustomFieldEntity(
            displayInProfileSummary = customFieldDto?.displayInProfileSummary,
            fieldData = customFieldDto?.fieldData,
            hint = customFieldDto?.hint,
            id = customFieldDto?.id,
            name = customFieldDto?.name,
            order = customFieldDto?.order,
            type = customFieldDto?.type
        )
    } ?: emptyList()

    return CustomProfileFields(customFields = customFieldsEntity)
}