package com.chocolate.viewmodel.mentions

import androidx.lifecycle.ViewModel
import com.chocolate.viewmodel.mentions.state.MentionsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MentionsViewModel : ViewModel() {

    private val _state = MutableStateFlow(MentionsUiState())
     val state = _state.asStateFlow()


}