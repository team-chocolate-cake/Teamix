package com.chocolate.viewmodel.directmessage

interface DirectMessageInteractions {
    fun onChangeSearchQuery(searchQuery: String)
    fun onClickDeleteQuery()
    fun onClickNewChat()
    fun onClickChat(id: String, name: String)

}