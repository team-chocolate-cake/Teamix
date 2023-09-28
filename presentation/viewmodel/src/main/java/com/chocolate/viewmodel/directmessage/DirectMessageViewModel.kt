package com.chocolate.viewmodel.directmessage

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.directmessage.ManageDirectMessageUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectMessageViewModel @Inject constructor(
    private val manageDirectMessageUseCase: ManageDirectMessageUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
) : BaseViewModel<DirectMessageUiState, DirectMessageUiEffect>(DirectMessageUiState()),
    DirectMessageInteractions {
    init {
        viewModelScope.launch {
            val currentMemberId = getCurrentMemberUseCase().id
            manageDirectMessageUseCase.getAllChatsById(currentMemberId)
                .combine(state.value.searchInput) { chats, searchQuery ->
                    manageDirectMessageUseCase.searchInDirectMessageChats(chats, searchQuery)
                }.collect {
                    _state.update { uiState ->
                        uiState.copy(chats = it.toUiState(), isLoading = false)
                    }
                }
        }
    }

    override fun onChangeSearchQuery(searchQuery: String) {
        viewModelScope.launch { _state.value.searchInput.emit(searchQuery) }
    }

    override fun onClickDeleteQuery() {
        viewModelScope.launch { _state.value.searchInput.emit("") }
    }

    override fun onClickNewChat() {
        sendUiEffect(DirectMessageUiEffect.NavigateToChooseMember)
    }

    override fun onClickChat(id: String, name: String) {
        sendUiEffect(DirectMessageUiEffect.NavigateToChat(id, name))
    }
}