package com.chocolate.entities.server_and_organizations

data class Linkifier(
    val id: Int,
    val pattern: String,
    val urlTemplate: String
)