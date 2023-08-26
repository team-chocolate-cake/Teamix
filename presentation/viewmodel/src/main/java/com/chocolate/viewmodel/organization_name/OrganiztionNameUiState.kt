package com.chocolate.viewmodel.organization_name

import com.chocolate.entities.uills.Empty

data class OrganizationNameUiState(
    val organizationName: String = String.Empty,
    val isLoading: Boolean = false,
    val isLogged: Boolean = false,
    val onboardingState: Boolean = false,
    val error: String? = null
)