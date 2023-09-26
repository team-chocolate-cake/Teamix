package com.chocolate.viewmodel.choosemember

interface ChooseMemberInteraction {
    fun onClickMemberItem(memberItemUiState: SelectedMemberItemUiState)
    fun onChangeSearchQuery(query: String)
    fun onRemoveSelectedItem(memberId: String)
    fun onActionBarTextClick()
    fun onClickRetry()
}