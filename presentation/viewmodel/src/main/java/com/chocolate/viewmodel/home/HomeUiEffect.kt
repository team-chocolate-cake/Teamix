package com.chocolate.viewmodel.home

sealed interface HomeUiEffect{
    object NavigationToDrafts: HomeUiEffect
    object NavigationToStarred: HomeUiEffect
    object NavigationToSavedLater: HomeUiEffect
    object NavigateToChannel: HomeUiEffect
    object NavigateToOrganizationName: HomeUiEffect
    object NavigateToTopic: HomeUiEffect
}
