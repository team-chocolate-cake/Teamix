package com.chocolate.viewmodel.conversation

import androidx.lifecycle.SavedStateHandle
import com.chocolate.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ConversationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): BaseViewModel<ConversationUiState,Unit>(ConversationUiState()), ConversationInteraction {
    private val args = ConversationArgs(savedStateHandle)

    override fun onMessageInputChanged(text: String) {
        _state.update { it.copy(messageInput = text) }
    }

    override fun onSendMessage() {

    }
}