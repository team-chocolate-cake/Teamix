package com.chocolate.viewmodel.choosemember

import com.chocolate.entities.entity.Member

@JvmName("membersToMembersUiState")
fun List<Member>.toUiState(selectedMembersId: List<SelectedMemberItemUiState>): List<SelectedMemberItemUiState>{
    return this.map { member ->
        val isSelected = selectedMembersId.map { it.memberId }.contains(member.id)
        member.toUserUiState(isSelected)
    }
}
@JvmName("memberToMemberUiState")
fun Member.toUserUiState(isSelected: Boolean): SelectedMemberItemUiState{
    return SelectedMemberItemUiState(
        memberId = this.id,
        imageUrl = this.imageUrl,
        name = this.name,
        isSelected = isSelected
    )
}