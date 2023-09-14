package com.chocolate.viewmodel.directMessage

interface DirectMessageInteractions {
    fun onChangeSearchQuery(search: String)
    fun onClickDeleteQuery()
    fun onClickNewChat()
    fun onClickChat(id: String, name: String)

}