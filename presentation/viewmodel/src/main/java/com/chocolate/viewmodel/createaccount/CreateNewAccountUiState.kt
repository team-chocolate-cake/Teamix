package com.chocolate.viewmodel.createaccount

import com.chocolate.entities.utils.Empty

data class CreateNewAccountUiState(
    val organizationsName: String = String.Empty,
    val isLoading: Boolean =false,
    val error: String? = null
)
