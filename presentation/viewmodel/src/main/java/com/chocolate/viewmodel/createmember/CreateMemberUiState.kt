package com.chocolate.viewmodel.createmember

import android.net.Uri
import com.chocolate.entities.Member
import com.chocolate.entities.UserRole
import com.chocolate.entities.utils.Empty
import com.chocolate.entities.utils.getRandomId

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

fun CreateMemberUiState.toEntity(role:String) = Member(
    id = getRandomId().toString(),
    name = fullName,
    email = email,
    imageUrl = personalImageUri.toString(),
    password = password,
    role = UserRole.fromValue(role),
    isActive = true,
    status = "",
)