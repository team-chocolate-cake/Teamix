package com.chocolate.viewmodel.choosemember

interface ChooseMemberInteraction {
    fun onClickMemberItem(memberId: String)
    fun onChangeSearchQuery(query: String)
    fun onRemoveSelectedItem(memberId: String)
    fun addMembersInChannel(channelName: String, usersId: List<String>)
    fun onClickRetry()
}