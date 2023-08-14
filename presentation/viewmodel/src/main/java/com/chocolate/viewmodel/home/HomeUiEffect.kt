package com.chocolate.viewmodel.home

sealed interface HomeUiEffect

object NavigationToMention: HomeUiEffect
object NavigationToDrafts: HomeUiEffect
object NavigationToStarred: HomeUiEffect
object NavigationToSavedLater: HomeUiEffect
object NavigateToChaNNel: HomeUiEffect