package com.chocolate.viewmodel.draft

import com.chocolate.entities.draft.Draft
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.usecases.draft.ManageDraftsUseCases
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DraftViewModel @Inject constructor(
    private val manageDraftsUseCases: ManageDraftsUseCases,
    private val stringsResource: StringsResource
) : BaseViewModel<DraftUiState, Unit>(DraftUiState()), DraftInteraction {

    init {
        getData()
    }

    override fun onClickDismisse() {
        TODO("Not yet implemented")
    }

    override fun onClickRetry() {
        getData()
    }

    private fun getData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            manageDraftsUseCases::getDrafts,
            ::onGetDraftsSuccess,
            ::onError
        )
    }

    private fun onGetDraftsSuccess(drafts: List<Draft>) {
        _state.update { it.copy(isLoading = false, error = null, messages = drafts.toUiState()) }
    }

    private fun onError(throwable: Throwable) {
        val error = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(isLoading = false, error = error) }
    }

}
