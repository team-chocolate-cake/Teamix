package com.chocolate.viewmodel.dm_choose_member

interface DMChooseMemberInteraction {

    fun onClickRetry()
    fun onChangeSearchQuery(input:String)
    fun onRemoveSelectedItem(item:Int)
    fun onClickMemberItem(memberId: Int)

}