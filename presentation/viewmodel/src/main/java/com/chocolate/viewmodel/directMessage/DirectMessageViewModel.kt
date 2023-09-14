package com.chocolate.viewmodel.directMessage

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.directmessage.GetAllChatsByIdUseCase
import com.chocolate.usecases.directmessage.SearchInDirectMessageChatsUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class DirectMessageViewModel @Inject constructor(
    private val getAllChatsByIdUseCase: GetAllChatsByIdUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val manageOrganizationDetails: ManageOrganizationDetailsUseCase,
    private val searchInDirectMessageChatsUseCase: SearchInDirectMessageChatsUseCase
) : BaseViewModel<DirectMessageUiState, DirectMessageUiEffect>(DirectMessageUiState()),
    DirectMessageInteractions {

    init {
        viewModelScope.launch {
            val currentMemberId = getCurrentMemberUseCase().id
            val currentOrganization = manageOrganizationDetails.getOrganizationName()
            collectFlow(getAllChatsByIdUseCase(currentMemberId, currentOrganization)) { chats ->
                searchInDirectMessageChatsUseCase.setChat(chats)
                this.copy(
                    chats = chats.toUiState()
                )
            }
            collectFlow(state.value.searchInput.debounce(1000).distinctUntilChanged()) {
                this.copy(chats = searchInDirectMessageChatsUseCase(it).toUiState())
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