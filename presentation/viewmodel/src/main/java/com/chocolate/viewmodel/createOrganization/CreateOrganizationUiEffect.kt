package com.chocolate.viewmodel.createOrganization

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateOrganizationUiEffect : BaseViewModel.BaseUiEffect {
    data class NavigateToCreateMemberScreen(val role: String) : CreateOrganizationUiEffect
    object NavigateToHaveOrganization : CreateOrganizationUiEffect
}
