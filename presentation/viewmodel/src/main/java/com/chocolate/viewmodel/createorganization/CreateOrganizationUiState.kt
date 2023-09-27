package com.chocolate.viewmodel.createorganization

import com.chocolate.entities.Organization
import com.chocolate.entities.utils.Empty


data class CreateOrganizationUiState(
     val organizationName: String = String.Empty,
     val organizationImageUri: String = String.Empty,
     val isLoading: Boolean = false,
     val error: String? = null,
)