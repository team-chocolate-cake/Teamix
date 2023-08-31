package com.chocolate.viewmodel.createtopic

interface CreateTopicInteraction {

    fun onTopicNameChange(topicName: String)
    fun onTopicContentChange(content: String)
    fun onCreateClick()

}