package com.chocolate.viewmodel.login

data class LoginUiState(
    val email : String = "",
    val password: String = "",
    val organizationName: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val passwordVisibility : Boolean = false

)