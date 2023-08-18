package com.chocolate.viewmodel.home

sealed interface HomeUiEffect{
    object NavigationToDrafts: HomeUiEffect
    object NavigationToStarred: HomeUiEffect
    object NavigationToSavedLater: HomeUiEffect
    object NavigateToChaNNel: HomeUiEffect
    object NavigateToOrganizationName: HomeUiEffect
}
