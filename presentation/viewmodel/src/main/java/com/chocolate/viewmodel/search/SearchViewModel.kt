package com.chocolate.viewmodel.search

import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

): BaseViewModel<SearchUiState,SearchEffect>(SearchUiState()),SearchInteraction {
    override fun onClickChannelItem(channelId: Int) {
        sendUiEffect(SearchEffect.NavigateToChannel(channelId))
    }

    override fun onClickMemberItem(memberId: Int) {
        TODO("Not yet implemented")
    }
}