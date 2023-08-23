package com.chocolate.viewmodel.forgetPassowrd

data class ForgetPasswordUiState(
    val organizationsName: String = "",
    val isLoading: Boolean =false,
    val error: String? = null
)
