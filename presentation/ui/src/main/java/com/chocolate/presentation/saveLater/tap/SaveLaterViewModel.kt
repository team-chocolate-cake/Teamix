package com.chocolate.presentation.saveLater.tap

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SaveLaterViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SavedLaterUISate())
    val state = _state.asStateFlow()

    private val isBottomSheetShowing = mutableStateOf(false)
    private val selectedSavedItem = mutableStateOf<SavedItemUiState?>(null)

    private val useCase = GetSavedItemsUseCase()

    init {
        initData()
    }

    private fun initData() {
        _state.update {
            it.copy(
                inProgressSavedItem = useCase.getInProgressSavedItem().toUIState(
                    onComplete = ::completeItem
                ),
                archivedSavedItem = useCase.getArchivedSavedItem().toUIState(
                    onUnArchive = ::unarchiveItem
                ),
                completedSavedItem = useCase.getCompletedSavedItem().toUIState(
                    onArchive = ::moveToArchive,
                    moveToInProgress = ::moveToInProgress,
                    removeFromLater = ::removeFromCompleted
                ),
            )
        }
    }

    fun removeFromCompleted(savedItemUiState: SavedItemUiState) {
        val currentState = _state.value
        val currentCompletedItems = currentState.completedSavedItem.toMutableList()


        currentCompletedItems.indexOfFirst { it.saveItems.any { it.matches(savedItemUiState) } }
            .takeIf { it != -1 }
            ?.let { itemOfDayToUpdateIndex ->
                currentCompletedItems.removeIfAndTransform(itemOfDayToUpdateIndex) { itemOfDayToUpdate ->
                    itemOfDayToUpdate.saveItems.any { it.matches(savedItemUiState) }
                }
            }

        _state.update { it.copy(completedSavedItem = currentCompletedItems) }
    }


    fun completeItem(savedItemUiState: SavedItemUiState) {
        val currentState = _state.value
        val currentInProgressItems = currentState.inProgressSavedItem.toMutableList()
        val currentCompletedItems = currentState.completedSavedItem.toMutableList()

        moveItemToState(
            currentInProgressItems,
            currentCompletedItems,
            savedItemUiState,
            SavedItemState.COMPLETED
        )

        _state.update {
            it.copy(
                inProgressSavedItem = currentInProgressItems,
                completedSavedItem = currentCompletedItems
            )
        }
    }

    fun unarchiveItem(savedItemUiState: SavedItemUiState) {
        val currentState = _state.value
        val currentArchivedItems = currentState.archivedSavedItem.toMutableList()
        val currentCompletedItems = currentState.completedSavedItem.toMutableList()

        moveItemToState(
            currentArchivedItems,
            currentCompletedItems,
            savedItemUiState,
            SavedItemState.COMPLETED
        )

        _state.update {
            it.copy(
                archivedSavedItem = currentArchivedItems,
                completedSavedItem = currentCompletedItems
            )
        }
    }

    fun moveToArchive(savedItemUiState: SavedItemUiState) {
        val currentState = _state.value
        val currentCompletedItems = currentState.completedSavedItem.toMutableList()
        val currentArchivedItems = currentState.archivedSavedItem.toMutableList()

        moveItemToState(
            currentCompletedItems,
            currentArchivedItems,
            savedItemUiState,
            SavedItemState.ARCHIVED
        )

        _state.update {
            it.copy(
                completedSavedItem = currentCompletedItems,
                archivedSavedItem = currentArchivedItems
            )
        }
    }

    fun moveToInProgress(savedItemUiState: SavedItemUiState) {
        val currentState = _state.value
        val currentCompletedItems = currentState.completedSavedItem.toMutableList()
        val currentInProgressItems = currentState.inProgressSavedItem.toMutableList()

        moveItemToState(
            currentCompletedItems,
            currentInProgressItems,
            savedItemUiState,
            SavedItemState.IN_PROGRESS
        )

        _state.update {
            it.copy(
                completedSavedItem = currentCompletedItems,
                inProgressSavedItem = currentInProgressItems
            )
        }
    }

    fun onCardClicked(savedItemUiState: SavedItemUiState) {
        if (savedItemUiState.state == SavedItemState.COMPLETED) {
            isBottomSheetShowing.value = true
            selectedSavedItem.value = savedItemUiState
        }
    }
}



inline fun <T> MutableList<T>.removeIfAndTransform(index: Int, predicate: (T) -> Boolean): T? {
    return if (predicate(this[index])) removeAt(index) else null
}

private fun SavedItemUiState.matches(other: SavedItemUiState): Boolean =
    name == other.name && title == other.title && date == other.date && description == other.description

fun moveItemToState(
    sourceState: MutableList<SavedItemOfDay>,
    targetState: MutableList<SavedItemOfDay>,
    savedItemUiState: SavedItemUiState,
    newState: SavedItemState
) {
    sourceState.indexOfFirst {
        it.saveItems.any { it.matches(savedItemUiState) }
    }.takeIf { it != -1 }
        ?.let { itemOfDayToUpdateIndex ->
            sourceState.removeIfAndTransform(itemOfDayToUpdateIndex) { itemOfDayToUpdate ->
                itemOfDayToUpdate.saveItems.any {
                    it.matches(savedItemUiState)
                }
            }

            val updatedItemUiState = savedItemUiState.copy(state = newState)
            val existingItemOfDayIndex = targetState.indexOfFirst {
                it.date == savedItemUiState.date
            }

            if (existingItemOfDayIndex != -1) {
                targetState[existingItemOfDayIndex] =
                    targetState[existingItemOfDayIndex].copy(
                        saveItems = targetState[existingItemOfDayIndex].saveItems + updatedItemUiState
                    )
            } else {
                targetState.add(
                    0, SavedItemOfDay(savedItemUiState.date, listOf(updatedItemUiState))
                )
            }
        }
}