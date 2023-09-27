package com.chocolate.viewmodel.choosemember

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.util.EmptyOrganizationNameException
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.usecases.channel.ManageChannelUseCase
import com.chocolate.usecases.member.GetMembersInOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import com.chocolate.viewmodel.createchannel.CreateChannelArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseMemberViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMembersInOrganizationUseCase: GetMembersInOrganizationUseCase,
    private val createChannelUseCase: ManageChannelUseCase,
    private val stringsResource: StringsResource,
) : BaseViewModel<ChooseMemberUiState, ChooseMemberUiEffect>(ChooseMemberUiState()),
    ChooseMemberInteraction {

    private val createChannelArgs: CreateChannelArgs = CreateChannelArgs(savedStateHandle)

    init {
        getChannelName()
        getMembers()
        getActionBarActionText()
    }

    private fun getActionBarActionText() {
        val actionText = if (state.value.hasNoSelectedMember)
            stringsResource.cancel
        else
            stringsResource.createChannel
        _state.update { it.copy(actionBarActionText = actionText) }
    }

    private fun getMembers() {
        viewModelScope.launch {
            getMembersInOrganizationUseCase()
                .combine(state.value.searchQuery) { members, searchQuery ->
                    val membersItem = if (searchQuery.isNotBlank())
                        members.filter {
                            it.name.contains(searchQuery, true)
                        }
                    else members
                    membersItem.toUiState(_state.value.selectedMembers)
                }.collect {
                    _state.update { uiState ->
                        uiState.copy(membersItemUiState = it, isLoading = false)
                    }
                }
        }
    }

    override fun onChangeSearchQuery(query: String) {
        viewModelScope.launch { _state.value.searchQuery.emit(query) }
    }

    override fun onRemoveSelectedItem(memberId: String) {
        _state.value.selectedMembers.find { it.memberId == memberId }
            ?.let { selectedMemberItemUiState ->
                onClickMemberItem(selectedMemberItemUiState)
            }
    }

    override fun onActionBarTextClick() {
        if (_state.value.hasNoSelectedMember) {
            sendUiEffect(ChooseMemberUiEffect.NavigateToHome)
        } else {
            tryToExecute(
                {
                    createChannelUseCase.createChannel(
                        createChannelArgs.channelName,
                        _state.value.selectedMembers.map { it.memberId },
                        createChannelArgs.description,
                    )
                },
                ::onCreateChannelSuccess,
                ::onError
            )
        }
    }

    override fun onClickMemberItem(memberItemUiState: SelectedMemberItemUiState) {
        val selectedMembers = _state.value.selectedMembers.toMutableList()
        val isMemberSelected = selectedMembers.any { it.memberId == memberItemUiState.memberId }
        if (isMemberSelected) {
            selectedMembers.removeIf { memberItemUiState.memberId == it.memberId }
        } else {
            selectedMembers.add(memberItemUiState)
        }
        val updatedMembersItemUiState = _state.value.membersItemUiState.map {
            if (it.memberId == memberItemUiState.memberId) {
                it.copy(isSelected = !it.isSelected)
            } else
                it
        }
        val hasNoSelectedMember = selectedMembers.isEmpty()
        _state.update { currentState ->
            currentState.copy(
                selectedMembers = selectedMembers,
                membersItemUiState = updatedMembersItemUiState,
                hasNoSelectedMember = hasNoSelectedMember
            )
        }
        getActionBarActionText()
        getSelectedMember()
    }

    private fun getSelectedMember(): List<SelectedMemberItemUiState> {
        return _state.value.membersItemUiState.filter { it.isSelected }
    }

    override fun onClickRetry() {
        getMembers()
    }

    private fun getChannelName() {
        _state.update { it.copy(channelName = createChannelArgs.channelName) }
    }

    private fun onCreateChannelSuccess(isCompleted: Boolean) {
        if (isCompleted) {
            _state.update {
                it.copy(
                    isLoading = false,
                    error = null,
                    successMessage = stringsResource.successMessage
                )
            }
            sendUiEffect(ChooseMemberUiEffect.NavigateToHome)
        }
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            is EmptyOrganizationNameException -> stringsResource.organizationNameNotFound
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(error = errorMessage, isLoading = false, successMessage = null) }
    }
}