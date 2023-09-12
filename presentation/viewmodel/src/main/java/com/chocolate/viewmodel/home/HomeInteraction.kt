package com.chocolate.viewmodel.home

interface HomeInteraction {
    fun onClickDrafts()
    fun onClickSavedLater()
    fun onClickChannel(id: Int, name: String)
    fun onClickTopic(name: String)
    fun onClickFloatingActionButton()

    fun onClickRetryButton()
}