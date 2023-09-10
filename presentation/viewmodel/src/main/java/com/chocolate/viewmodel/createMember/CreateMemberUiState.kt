package com.chocolate.viewmodel.createMember

import android.net.Uri
import com.chocolate.entities.member.MemberInformation
import com.chocolate.entities.uills.Empty
import java.net.URI

data class CreateMemberUiState(
    val personalImageUri: Uri? = null,
    val fullName: String = String.Empty,
    val email: String = String.Empty,
    val password: String = String.Empty,
    val passwordVisibility: Boolean = false,
    val confirmPassword: String = String.Empty,
    val confirmPasswordVisibility: Boolean = false,
    val organizationName: String = String.Empty,
    val isLoading: Boolean = false,
    val error: String? = null
)

fun CreateMemberUiState.toEntity() = MemberInformation(
    fullName = fullName,
    email = email,
    personalImageUri = URI(personalImageUri.toString()),
    password = password,
    confirmPassword = confirmPassword

)