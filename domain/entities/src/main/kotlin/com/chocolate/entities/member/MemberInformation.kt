package com.chocolate.entities.member

import java.net.URI

data class MemberInformation(
    val fullName: String,
    val email: String,
    val personalImageUri: URI,
    val password: String,
    val confirmPassword: String,
)
