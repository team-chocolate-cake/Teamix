package com.chocolate.viewmodel.createAccount

import com.chocolate.entities.uills.Empty

data class CreateNewAccountUiState(
    val organizationsName: String = String.Empty,
    val isLoading: Boolean =false,
    val error: String? = null
)
