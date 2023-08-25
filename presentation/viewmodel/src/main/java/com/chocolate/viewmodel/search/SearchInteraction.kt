package com.chocolate.viewmodel.search

interface SearchInteraction {
    fun onClickChannelItem(channelId: Int)
    fun onClickMemberItem(memberId: Int)
    fun onChangeSearchQuery(query: String)
    fun onChangeTabIndex(tabIndex: Int)
    fun onClickRecentSearchItem(text: String)
    fun onClickRetry()
}