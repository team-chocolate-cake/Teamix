package com.chocolate.viewmodel.home

interface HomeInteraction {
    fun onClickDrafts()
    fun onClickStarred()
    fun onClickSavedLater()
    fun onClickChannel(id: Int, name: String)
    fun onClickTopic(channelId:Int,topicId:Int,name: String)
    fun onClickFloatingActionButton()

    fun onClickRetryButton()
}