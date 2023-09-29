package com.chocolate.viewmodel.directmessagechoosemember

interface DirectMessageChooseMemberInteraction {
    fun onClickRetry()
    fun onClickMemberItem(memberId: String)
    fun onClickOk()
}