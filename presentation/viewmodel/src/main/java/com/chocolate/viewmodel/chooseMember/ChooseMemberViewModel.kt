package com.chocolate.viewmodel.chooseMember

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.member.Member
import com.chocolate.usecases.channel.AddUsersInChannelByChannelNameAndUsersIdUseCase
import com.chocolate.usecases.member.GetMembersInOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import com.chocolate.viewmodel.createChannel.CreateChannelArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseMemberViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllUsers: GetMembersInOrganizationUseCase,
    private val addUsersInChannel:
    AddUsersInChannelByChannelNameAndUsersIdUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<ChooseMemberUiState, Unit>(ChooseMemberUiState()), ChooseMemberInteraction {
    private val createChannelArgs: CreateChannelArgs = CreateChannelArgs(savedStateHandle)

    init {
        getChannelName()
        getUsers()
    }

    override fun onChangeSearchQuery(query: String) {
        _state.update { it.copy(isLoading = true, searchQuery = query) }
        tryToExecuteFlow(
            { getAllUsers.searchUser(query) },
            ::onChangeSearchSuccess,
            ::onChangeSearchError
        )
    }

    override fun onRemoveSelectedItem(memberId: String) {
        _state.update { currentState ->
            val updatedSelectedMembersUiState =
                removeSelectedItem(memberId, currentState.selectedMembersUiState)
            val updatedMembersUiState = updateMembersSelectedState(
                currentState.membersUiState,
                updatedSelectedMembersUiState
            )
            currentState.copy(
                membersUiState = updatedMembersUiState,
                selectedMembersUiState = updatedSelectedMembersUiState
            )
        }
    }

    override fun addMembersInChannel(channelName: String, usersId: List<String>) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { addUsersInChannel(channelName, usersId) },
            ::onAddMembersInChannelSuccess,
            ::onAddMembersInChannelError
        )
    }

    override fun onClickMemberItem(memberId: String) {
        _state.update { currentState ->
            val updatedMembersUiState = toggleMemberSelection(currentState.membersUiState, memberId)
            val updatedSelectedMembersUiState =
                updateSelectedMembers(currentState, memberId).distinct()
            currentState.copy(
                membersUiState = updatedMembersUiState,
                selectedMembersUiState = updatedSelectedMembersUiState
            )
        }
    }

    override fun onClickRetry() {
        getUsers()
    }

    private fun getChannelName() {
        _state.update { it.copy(channelName = createChannelArgs.channelName) }
    }

    private fun getUsers() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(getAllUsers::invoke, ::onGetUsersSuccess, ::onGetUsersError)
    }

    private fun onGetUsersSuccess(members: Flow<List<Member>>) {
        viewModelScope.launch {
            members.collect{members->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        membersUiState = members.toUsersUiState()
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

    private fun onAddMembersInChannelSuccess(isCompleted: Boolean) {
        if (isCompleted) {
            _state.update {
                it.copy(
                    isLoading = false,
                    error = null,
                    successMessage = stringsResource.successMessage
                )
            }
        }
    }

    private fun onAddMembersInChannelError(throwable: Throwable) {
        _state.update {
            it.copy(
                isLoading = false,
                error = getMessageError(throwable),
                successMessage = null
            )
        }
    }

    private fun toggleMemberSelection(
        membersUiState: List<ChooseMembersUiState>,
        memberId: String
    ): List<ChooseMembersUiState> {
        return membersUiState.map { memberUiState ->
            if (memberUiState.userId == memberId) {
                memberUiState.copy(isSelected = !memberUiState.isSelected)
            } else {
                memberUiState
            }
        }
    }

    private fun updateSelectedMembers(
        currentState: ChooseMemberUiState,
        memberId: String
    ): List<SelectedMembersUiState> {
        val selectedMemberUiState = currentState.membersUiState.find { it.userId == memberId }
        val isMemberSelected = selectedMemberUiState?.isSelected ?: false

        return if (isMemberSelected) {
            removeSelectedMember(currentState.selectedMembersUiState, memberId)
        } else {
            addSelectedMember(currentState.selectedMembersUiState, selectedMemberUiState)
        }
    }

    private fun removeSelectedMember(
        selectedMembers: List<SelectedMembersUiState>,
        memberId: String
    ): List<SelectedMembersUiState> {
        return selectedMembers.filterNot { it.userId == memberId }
    }

    private fun addSelectedMember(
        selectedMembers: List<SelectedMembersUiState>,
        selectedMemberUiState: ChooseMembersUiState?
    ): List<SelectedMembersUiState> {
        return selectedMemberUiState?.let { selectedMember ->
            selectedMembers + SelectedMembersUiState(
                userId = selectedMember.userId,
                imageUrl = selectedMember.imageUrl,
                name = selectedMember.name
            )
        } ?: selectedMembers
    }

    private fun removeSelectedItem(
        memberId: String,
        selectedMembers: List<SelectedMembersUiState>
    ): List<SelectedMembersUiState> {
        return selectedMembers.filterNot { it.userId == memberId }
    }

    private fun updateMembersSelectedState(
        membersUiState: List<ChooseMembersUiState>,
        selectedMembers: List<SelectedMembersUiState>
    ): List<ChooseMembersUiState> {
        return membersUiState.map { memberUiState ->
            val isSelected = selectedMembers.any { it.userId == memberUiState.userId }
            memberUiState.copy(isSelected = isSelected)
        }
    }

    private fun onChangeSearchSuccess(members: Flow<List<Member>>) {
        viewModelScope.launch{
            members.collect{members->
                val membersUi = members.toUsersUiState()
                val updatedMembersUiState = membersUi.map { memberUi ->
                    val isSelected =
                        _state.value.selectedMembersUiState.any { it.userId == memberUi.userId }
                    memberUi.copy(isSelected = isSelected)
                }
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        membersUiState = updatedMembersUiState
                    )
                }
            }
        }
    }

    private fun onChangeSearchError(throwable: Throwable) {
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
}