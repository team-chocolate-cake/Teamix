package com.chocolate.viewmodel.chooseMember

interface ChooseMemberInteraction {
    fun onClickMemberItem(memberId: Int)
    fun onChangeSearchQuery(query: String)
}