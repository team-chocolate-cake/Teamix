package com.chocolate.viewmodel.createTopic

interface CreateTopicInteraction {
fun onTopicNameChange(name:String)
fun onTopicContentChange(content:String)
fun onCreateClick()
fun onErrorDismiss()
}