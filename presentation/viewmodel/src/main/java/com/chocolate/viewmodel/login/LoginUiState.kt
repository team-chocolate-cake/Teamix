package com.chocolate.viewmodel.login

import com.chocolate.entities.uills.Empty

data class LoginUiState(
    val email : String = String.Empty,
    val password: String = String.Empty,
    val organizationName: String = String.Empty,
    val isLogged: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val passwordVisibility : Boolean = false
)