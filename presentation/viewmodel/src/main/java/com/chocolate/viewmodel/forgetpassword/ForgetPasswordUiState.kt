package com.chocolate.viewmodel.forgetpassword

import com.chocolate.entities.util.Empty

data class ForgetPasswordUiState(
    val organizationsName: String = String.Empty,
    val isLoading: Boolean =false,
    val error: String? = null
)
