package com.chocolate.viewmodel.search

interface SearchInteraction {
    fun onClickChannelItem(channelId: Int)
    fun onClickMemberItem(memberId: Int)
}