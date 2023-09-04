package com.chocolate.viewmodel.draft

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.draft.Draft
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.usecases.draft.ManageDraftsUseCases
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DraftViewModel @Inject constructor(
    private val manageDraftsUseCases: ManageDraftsUseCases,
    private val stringsResource: StringsResource,
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
) : BaseViewModel<DraftsUiState, Unit>(DraftsUiState()), DraftInteraction {

    init {
        getDarkModeState()
        getData()
    }

    private fun getDarkModeState() {
        viewModelScope.launch {
            customizeProfileSettings.isDarkThem().collectLatest {isDark->
                _state.update { it.copy(isDarkModel = isDark) }
            }
        }
    }

    private fun getData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            manageDraftsUseCases::getDrafts,
            ::onGetDraftsSuccess,
            ::onError
        )
    }

    override fun deleteDraft(id: Int) {
        deleteDraftFromRemote(id)
        deleteDraftFromUi(id)
    }

    override fun onClickRetry() {
        getData()
    }

    private fun deleteDraftFromUi(id: Int) {
        _state.update { currentState ->
            val updatedDraftItems = currentState.draftItems.filter { it.id != id }
            currentState.copy(draftItems = updatedDraftItems)
        }
    }

    private fun deleteDraftFromRemote(id: Int) {
        tryToExecute(
            { manageDraftsUseCases.deleteDraft(id) },
            ::onDeleteDraftSuccess,
            ::onError
        )
    }

    private fun onDeleteDraftSuccess(unit: Unit) {
        _state.update { it.copy(showNoInternetLottie = false, error = null, isLoading = false) }
    }

    private fun onGetDraftsSuccess(drafts: List<Draft>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                showNoInternetLottie = false,
                draftItems = drafts.toUiState()
            )
        }
    }

    private fun onError(throwable: Throwable) {
        val error = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
        val noInternet = throwable is NoConnectionException
        _state.update {
            it.copy(
                isLoading = false,
                error = error,
                showNoInternetLottie = noInternet
            )
        }
    }

}
