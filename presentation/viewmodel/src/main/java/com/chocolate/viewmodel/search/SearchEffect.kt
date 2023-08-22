package com.chocolate.viewmodel.search

sealed interface SearchEffect{
    data class NavigateToChannel(val channelId: Int): SearchEffect
}