package com.chocolate.viewmodel.createaccount

import com.chocolate.entities.util.Empty

data class CreateNewAccountUiState(
    val organizationsName: String = String.Empty,
    val isLoading: Boolean =false,
    val error: String? = null
)
