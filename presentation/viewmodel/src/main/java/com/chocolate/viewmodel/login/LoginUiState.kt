package com.chocolate.viewmodel.login

import com.chocolate.viewmodel.base.BaseErrorUiState

data class LoginUiState(
    val email : String = "",
    val password: String = "",
    val nameOrganization: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val passwordVisibility : Boolean = false

)
