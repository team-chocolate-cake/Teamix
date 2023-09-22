package com.chocolate.viewmodel.directMessageChooseMember

interface DirectMessageChooseMemberInteraction {

    fun onClickRetry()
    fun onRemoveSelectedItem(item:String)
    fun onClickMemberItem(memberId: String)
    fun onClickOk()

}