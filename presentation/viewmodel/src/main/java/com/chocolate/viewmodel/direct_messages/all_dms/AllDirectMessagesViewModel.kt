package com.chocolate.viewmodel.direct_messages.all_dms

import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllDirectMessagesViewModel @Inject constructor(

) : BaseViewModel<AllDirectMessagesUiState, AllDirectMessagesUiEffect>(AllDirectMessagesUiState()),
    AllDirectMessagesInteraction {

}