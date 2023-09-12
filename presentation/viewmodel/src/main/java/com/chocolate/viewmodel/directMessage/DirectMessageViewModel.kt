package com.chocolate.viewmodel.directMessage

import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DirectMessageViewModel @Inject constructor(

): BaseViewModel<DirectMessageUiState,DirectMessageUiEffect>(DirectMessageUiState()) , DirectMessageInteractions{
    override fun onChangeSearchQuery(search: String) {
        _state.update { it.copy(searchInput = search) }
    }

    override fun onClickDeleteQuery() {
        _state.update { it.copy(searchInput = "") }
    }

    override fun onClickNewChat() {
        sendUiEffect(DirectMessageUiEffect.NavigateToChooseMember)
    }


}