package com.chocolate.viewmodel.dm_choose_member

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.member.Member
import com.chocolate.usecases.member.GetMembersInOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import com.chocolate.viewmodel.chooseMember.ChooseMemberUiState
import com.chocolate.viewmodel.chooseMember.ChooseMembersUiState
import com.chocolate.viewmodel.chooseMember.SelectedMembersUiState
import com.chocolate.viewmodel.chooseMember.toUsersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DMDMChooseMemberViewModel @Inject constructor(
    private val getAllUsers: GetMembersInOrganizationUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<DMChooseMemberUiState, Unit>(DMChooseMemberUiState()), DMChooseMemberInteraction {

    init {
        getUsers()
    }

    private fun getUsers() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(getAllUsers::invoke, ::onGetUsersSuccess, ::onGetUsersError)
    }

    private fun onGetUsersSuccess(members: Flow<List<Member>>) {
        viewModelScope.launch {
            members.collectLatest {members->
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
    private fun getMessageError(throwable: Throwable): String {
        return when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
    }

    override fun onClickRetry() {
        getUsers()
    }

    override fun onChangeSearchQuery(input: String) {
        _state.update { it.copy(searchQuery = input) }
    }

    override fun onRemoveSelectedItem(item: Int) {
        _state.update { currentState ->
            val updatedSelectedMembersUiState =
                removeSelectedItem(item, currentState.selectedMembersUiState)
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
    private fun removeSelectedItem(
        memberId: Int,
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
    override fun onClickMemberItem(memberId: Int) {
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
    private fun updateSelectedMembers(
        currentState: DMChooseMemberUiState,
        memberId: Int
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
        memberId: Int
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
    private fun toggleMemberSelection(
        membersUiState: List<ChooseMembersUiState>,
        memberId: Int
    ): List<ChooseMembersUiState> {
        return membersUiState.map { memberUiState ->
            if (memberUiState.userId == memberId) {
                memberUiState.copy(isSelected = !memberUiState.isSelected)
            } else {
                memberUiState
            }
        }
    }
}