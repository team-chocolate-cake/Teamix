package com.chocolate.viewmodel.organization_name

import com.chocolate.viewmodel.base.BaseViewModel

data class OrganizationNameUiState(
    val organizationName: String ="",
    val isLoading: Boolean = false,
    val isLogged: Boolean = false,
    val onboardingState: Boolean = false,
    val error: String? = null
    ): BaseViewModel.BaseUiState