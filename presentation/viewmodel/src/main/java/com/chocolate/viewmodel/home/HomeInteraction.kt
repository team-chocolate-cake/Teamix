package com.chocolate.viewmodel.home

interface HomeInteraction {
    fun onClickDrafts()
    fun onClickStarred()
    fun onClickSavedLater()
    fun onClickChannel(id: Int, name: String)
    fun onClickTopic(name: String)
    fun onClickFloatingActionButton()
}