package com.chocolate.viewmodel.search

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface SearchEffect: BaseViewModel.BaseUiEffect{
    data class NavigateToChannel(val channelId: Int): SearchEffect
}