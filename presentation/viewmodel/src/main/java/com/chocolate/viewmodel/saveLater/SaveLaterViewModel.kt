package com.chocolate.viewmodel.saveLater

import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveLaterViewModel @Inject constructor(

) : BaseViewModel<SaveLaterMessageUiState, SaveLaterEffect>(SaveLaterMessageUiState()),
    SaveLaterInteraction {
    init {
        getAllSavedMessages()
    }

    private fun getAllSavedMessages() {

    }

    override fun onDismissMessage(messageId: Int) {

    }
}