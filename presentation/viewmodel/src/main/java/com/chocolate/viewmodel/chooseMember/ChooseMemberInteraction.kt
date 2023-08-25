package com.chocolate.viewmodel.chooseMember

interface ChooseMemberInteraction {
    fun onClickMemberItem(memberId: Int)
    fun onChangeSearchQuery(query: String)
    fun onRemoveSelectedItem(memberId: Int)
    fun addMembersInChannel(channelName: String, usersId: List<Int>)
    fun onClickRetry()
}