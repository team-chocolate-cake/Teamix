package com.chocolate.viewmodel.createorganization

import com.chocolate.entities.organization.Organization
import com.chocolate.entities.uills.Empty


data class CreateOrganizationUiState(
     val organizationName: String = String.Empty,
     val organizationImageUri: String = String.Empty,
     val isLoading: Boolean = false,
     val error: String? = null,
)