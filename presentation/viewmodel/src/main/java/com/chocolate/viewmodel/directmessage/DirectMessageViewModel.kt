package com.chocolate.viewmodel.directmessage

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.entity.Chat
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.usecases.directmessage.ManageDirectMessageUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectMessageViewModel @Inject constructor(
    private val manageDirectMessageUseCase: ManageDirectMessageUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<DirectMessageUiState, DirectMessageUiEffect>(DirectMessageUiState()),
    DirectMessageInteractions {
    init {
        getAllChatsById()
    }

    private fun getAllChatsById() {
        tryToExecuteFlow(
            flowBlock = {
                manageDirectMessageUseCase.getAllChatsById(getCurrentMemberUseCase().id)
                    .combine(state.value.searchInput) { chats, searchQuery ->
                        manageDirectMessageUseCase.searchInDirectMessageChats(chats, searchQuery)
                    }
            },
            onSuccess = { onGetAllChatsSuccess(it) },
            onError = { onError(it) }
        )
    }

    private suspend fun onGetAllChatsSuccess(flow: Flow<List<Chat>>) {
        flow.collectLatest { chats ->
            _state.update { it.copy(chats = chats.toUiState(), isLoading = false) }
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

    private fun onError(throwable: Throwable) {
        val error = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(error = error) }
    }
}