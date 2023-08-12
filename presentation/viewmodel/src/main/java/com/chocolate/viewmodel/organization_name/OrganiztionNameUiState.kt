package com.chocolate.viewmodel.organization_name

import com.chocolate.viewmodel.base.BaseErrorUiState

data class OrganizationNameUiState(
    val organizationName: String ="",
    val isLoading: Boolean = false,
    val isShowOnBorading: Boolean = false,
    val error: String? = null
    )