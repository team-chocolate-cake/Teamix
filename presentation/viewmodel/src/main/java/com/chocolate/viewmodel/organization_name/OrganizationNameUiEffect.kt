package com.chocolate.viewmodel.organization_name

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface OrganizationNameUiEffect: BaseViewModel.BaseUiEffect{
     object NavigateToLoginScreen: OrganizationNameUiEffect
     object NavigateToCreateOrganization: OrganizationNameUiEffect
}