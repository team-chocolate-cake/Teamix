package com.chocolate.viewmodel.home

interface HomeInteraction {
    fun onClickDrafts()
    fun onClickSavedLater()
    fun onClickChannel(id: String, name: String)
    fun onClickTopic(channelId:String,topicId:String,name: String)
    fun onClickFloatingActionButton()

    fun onClickRetryButton()
}