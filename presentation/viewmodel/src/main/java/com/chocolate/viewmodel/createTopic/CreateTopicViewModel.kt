package com.chocolate.viewmodel.createTopic

import androidx.lifecycle.SavedStateHandle
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.topic.TopicArgs
import javax.inject.Inject

class CreateTopicViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel<CreateTopicUiState, CreateTopicEffect>(CreateTopicUiState()),
    CreateTopicInteraction {

    private val topicArgs = TopicArgs(savedStateHandle)

    override fun onTopicNameChange(name: String) {

    }

    override fun onTopicContentChange(content: String) {

    }

    override fun onCreateClick() {
        topicArgs
    }

    override fun onErrorDismiss() {
        
    }
}