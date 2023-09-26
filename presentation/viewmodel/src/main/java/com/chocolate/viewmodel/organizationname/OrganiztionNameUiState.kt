package com.chocolate.viewmodel.organizationname

import com.chocolate.entities.uills.Empty

data class OrganizationNameUiState(
    val organizationName: String = String.Empty,
    val isLoading: Boolean = false,
    val onboardingState: Boolean = false,
    val error: String? = null,
    val showSnakeBar:Boolean=false
)