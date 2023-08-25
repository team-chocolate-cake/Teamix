package com.chocolate.viewmodel.home

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface HomeUiEffect: BaseViewModel.BaseUiEffect{
    object NavigationToDrafts: HomeUiEffect
    object NavigationToStarred: HomeUiEffect
    object NavigationToSavedLater: HomeUiEffect
    object NavigateToChannel: HomeUiEffect
    object NavigateToOrganizationName: HomeUiEffect
    object NavigateToTopic: HomeUiEffect
    object NavigateToCreateChannel: HomeUiEffect
}
