package com.chocolate.viewmodel.saveLater

import com.chocolate.viewmodel.base.BaseViewModel

class SaveLaterViewModel : BaseViewModel<SaveLaterMessageUiState, SaveLaterEffect>(SaveLaterMessageUiState()),SaveLaterInteraction {
    init {
        getAllSavedMessages()
    }

    fun getAllSavedMessages() {

    }

    override fun onDismissMessage(messageId: Int) {

    }
}