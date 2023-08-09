package com.chocolate.entities.server_and_organizations

data class CustomProfileFields(
    val customFields: List<CustomFieldEntity>
)

data class CustomFieldEntity(
    val displayInProfileSummary: Boolean,
    val fieldData: String,
    val hint: String,
    val id: Int,
    val name: String,
    val order: Int,
    val type: Int
)