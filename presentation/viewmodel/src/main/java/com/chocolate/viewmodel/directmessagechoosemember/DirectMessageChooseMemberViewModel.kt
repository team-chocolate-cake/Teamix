package com.chocolate.viewmodel.directmessagechoosemember

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.member.Member
import com.chocolate.usecases.directmessage.CreateNewChatUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.member.GetMembersExceptCurrentMemberUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectMessageChooseMemberViewModel @Inject constructor(
    private val getMembersExceptCurrentMemberUseCase: GetMembersExceptCurrentMemberUseCase,
    private val stringsResource: StringsResource,
    private val createNewChatUseCase: CreateNewChatUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
) : BaseViewModel<DirectMessageChooseMemberUiState, DirectMessageChooseMemberUiEffect>(
    DirectMessageChooseMemberUiState()
), DirectMessageChooseMemberInteraction {

    init {
        getMembers()
    }

    private fun getMembers() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getMembersExceptCurrentMemberUseCase() },
            ::onGetUsersSuccess,
            ::onGetUsersError
        )
    }

    private fun onGetUsersSuccess(members: Flow<List<Member>>) {
        viewModelScope.launch {
            members.collectLatest { members ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        membersUiState = members.toDMMemberUiState()
                    )
                }
            }

        }
    }

    private fun onGetUsersError(throwable: Throwable) {
        _state.update {
            it.copy(
                error = getMessageError(throwable),
                isLoading = false,
                successMessage = null
            )
        }
    }

    private fun getMessageError(throwable: Throwable): String {
        return when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
    }

    override fun onClickRetry() {
        getMembers()
    }

    override fun onRemoveSelectedItem(item: String) {
        _state.update { it.copy(selectedMembersUiState = null) }
    }

    override fun onClickMemberItem(memberId: String) {
        _state.update {
            val updatedMember = it.membersUiState.map {
                if (it.userId == memberId) it.copy(isSelected = !it.isSelected)
                else it.copy(isSelected = false)
            }
            it.copy(
                selectedMembersUiState = it.membersUiState.find { it.userId == memberId },
                membersUiState = updatedMember
            )
        }
    }

    override fun onClickOk() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val users: ArrayList<String> = ArrayList()
            users.add(getCurrentMemberUseCase().id)
            users.add(state.value.selectedMembersUiState!!.userId)
            val groupId = createNewChatUseCase(
                users.toList(),
            )
            sendUiEffect(DirectMessageChooseMemberUiEffect.NavigateToDmChat(groupId))
        }
    }
}