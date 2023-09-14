package com.chocolate.viewmodel.dm_choose_member

interface DMChooseMemberInteraction {

    fun onClickRetry()
    fun onRemoveSelectedItem(item:String)
    fun onClickMemberItem(memberId: String)
    fun onClickOk()

}