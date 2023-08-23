package com.chocolate.viewmodel.organization_name

data class OrganizationNameUiState(
    val organizationName: String = "",
    val isLoading: Boolean = false,
    val onboardingState: Boolean = false,
    val error: String? = null
)