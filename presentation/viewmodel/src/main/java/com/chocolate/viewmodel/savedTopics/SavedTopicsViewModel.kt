package com.chocolate.viewmodel.savedTopics

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.usecases.topic.ManageTopicUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedTopicsViewModel @Inject constructor(
    private val manageTopicUseCase: ManageTopicUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<SavedTopicsUiState, Unit>(SavedTopicsUiState()), SavedTopicsInteraction {

    init {
        getSavedTopics()
    }

    private fun getSavedTopics() {
        _state.update { it.copy(error = null, isLoading = true) }
        viewModelScope.launch {
            collectFlow(manageTopicUseCase.getSavedTopics()) {
                this.copy(error = null, isLoading = false, topics = it.toSavedTopicsItemsUiState())
            }
        }
    }

    override fun onDismissTopic(savedTopicId: String) {
        tryToExecute(
            { manageTopicUseCase.deleteTopicById(savedTopicId) },
            ::onDeleteMessageSuccess,
            ::onError
        )
    }

    private fun onDeleteMessageSuccess(unit: Unit) {
        _state.update { it.copy(error = null, isLoading = false) }
    }

    private fun onError(throwable: Throwable) {
        val error = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(isLoading = false, error = error) }
    }
}
