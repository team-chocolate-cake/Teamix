package com.chocolate.viewmodel.createtopic

interface CreateTopicInteraction {
fun onTopicNameChange(name:String)
fun onTopicContentChange(content:String)
fun onCreateClick()
fun onErrorDismiss()
}