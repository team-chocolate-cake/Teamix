package com.chocolate.viewmodel.home

interface HomeInteraction {
    fun onClickDrafts()
    fun onClickSavedLater()
    fun onClickChannel(id: Int, name: String)
    fun onClickTopic(channelId:Int,topicId:String,name: String)
    fun onClickFloatingActionButton()

    fun onClickRetryButton()
}