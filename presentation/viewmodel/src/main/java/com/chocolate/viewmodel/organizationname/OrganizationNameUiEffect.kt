package com.chocolate.viewmodel.organizationname

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface OrganizationNameUiEffect: BaseViewModel.BaseUiEffect{
     object NavigateToLoginScreen: OrganizationNameUiEffect
     object NavigateToCreateOrganization: OrganizationNameUiEffect
     object ShowSnackBar: OrganizationNameUiEffect
}