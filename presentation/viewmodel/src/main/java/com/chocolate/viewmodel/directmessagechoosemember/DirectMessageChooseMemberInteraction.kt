package com.chocolate.viewmodel.directmessagechoosemember

interface DirectMessageChooseMemberInteraction {

    fun onClickRetry()
    fun onRemoveSelectedItem(item:String)
    fun onClickMemberItem(memberId: String)
    fun onClickOk()

}