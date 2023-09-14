package com.chocolate.viewmodel.createOrganization

import android.net.Uri
import com.chocolate.entities.uills.Empty


data class CreateOrganizationUiState (
     val organizationName: String = String.Empty,
     val personalImageUri: Uri? = null,
     val isLoading: Boolean = false,
     val error: String? = null,
)
