package com.chocolate.viewmodel.directMessage

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.direct_message.GetAllChatsByIdUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class DirectMessageViewModel @Inject constructor(
    private val getAllChatsByIdUseCase: GetAllChatsByIdUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val manageOrganizationDetails: ManageOrganizationDetailsUseCase,
) : BaseViewModel<DirectMessageUiState, DirectMessageUiEffect>(DirectMessageUiState()),
    DirectMessageInteractions {

    init {
        viewModelScope.launch {
            val currentMemberId = getCurrentMemberUseCase().id
            val currentOrganization = manageOrganizationDetails.getOrganizationName()
            collectFlow(getAllChatsByIdUseCase(currentMemberId, currentOrganization)){chats->
                _state.value.copy(
                    chats =chats.map { it.toUiState() }.reversed()
                )
            }
        }
    }

    override fun onChangeSearchQuery(search: String) {
        _state.update { it.copy(searchInput = search) }
    }

    override fun onClickDeleteQuery() {
        _state.update { it.copy(searchInput = "") }
    }

    override fun onClickNewChat() {
        sendUiEffect(DirectMessageUiEffect.NavigateToChooseMember)
    }

    override fun onClickChat(id: String, name: String) {
        sendUiEffect(DirectMessageUiEffect.NavigateToChat(id , name))
    }


}