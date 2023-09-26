package com.chocolate.repository.model.dto.member

data class MemberDto(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val imageUrl: String? = null,
    val password: String? = null,
    @field:JvmField val isActive: Boolean = true,
    val role: String? = null,
    val status: String? = null,
)