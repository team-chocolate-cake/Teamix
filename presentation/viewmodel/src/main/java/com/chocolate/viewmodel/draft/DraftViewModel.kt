package com.chocolate.viewmodel.draft

import com.chocolate.usecases.draft.ManageDraftsUseCases
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DraftViewModel @Inject constructor(
    private val manageDraftsUseCases: ManageDraftsUseCases
) : BaseViewModel<DraftUiState, Unit>(DraftUiState()), DraftInteraction {

}
