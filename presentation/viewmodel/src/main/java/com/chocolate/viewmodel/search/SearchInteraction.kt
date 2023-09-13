package com.chocolate.viewmodel.search

interface SearchInteraction {
    fun onClickChannelItem(id: String, name: String)
    fun onChangeSearchQuery(query: String)
    fun onClickRetry()
    fun onClickDeleteQuery()
}