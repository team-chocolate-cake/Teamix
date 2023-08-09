package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.CustomFieldEntity
import com.chocolate.entities.server_and_organizations.CustomProfileFields
import com.chocolate.repository.dto.remote.server_and_organizations.response.CustomProfileFieldsDto

fun CustomProfileFieldsDto.toCustomProfileFields(): CustomProfileFields {
    val customFieldsEntity = customFieldDtos?.map { customFieldDto ->
        CustomFieldEntity(
            displayInProfileSummary = customFieldDto.displayInProfileSummary ?: false,
            fieldData = customFieldDto.fieldData ?: "",
            hint = customFieldDto.hint ?: "",
            id = customFieldDto.id ?: 0,
            name = customFieldDto.name ?: "",
            order = customFieldDto.order ?: 0,
            type = customFieldDto.type ?: 0
        )
    } ?: emptyList()
    return CustomProfileFields(customFields = customFieldsEntity)
}