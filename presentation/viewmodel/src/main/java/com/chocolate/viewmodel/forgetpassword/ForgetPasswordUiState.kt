package com.chocolate.viewmodel.forgetpassword

import com.chocolate.entities.utils.Empty

data class ForgetPasswordUiState(
    val organizationsName: String = String.Empty,
    val isLoading: Boolean =false,
    val error: String? = null
)
