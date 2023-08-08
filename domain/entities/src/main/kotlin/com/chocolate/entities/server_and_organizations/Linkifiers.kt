package com.chocolate.entities.server_and_organizations

data class LinkifiersEntity(
val linkifiers: List<LinkifierEntity>
)
data class LinkifierEntity(
    val id: Int,
    val pattern: String,
    val urlTemplate: String
)