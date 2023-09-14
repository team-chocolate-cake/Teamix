package com.chocolate.viewmodel.createOrganization

import com.chocolate.entities.uills.Empty


data class CreateOrganizationUiState (
     val organizationName: String = String.Empty,
     val isLoading: Boolean = false,
     val error: String? = null,
)
