package com.chocolate.viewmodel.createmember

import android.net.Uri
import com.chocolate.entities.util.Empty

data class CreateMemberUiState(
    val personalImageUri: Uri? = null,
    val fullName: String = String.Empty,
    val email: String = String.Empty,
    val password: String = String.Empty,
    val passwordVisibility: Boolean = false,
    val confirmPassword: String = String.Empty,
    val confirmPasswordVisibility: Boolean = false,
    val organizationName: String = String.Empty,
    val isCreatingMember: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)

