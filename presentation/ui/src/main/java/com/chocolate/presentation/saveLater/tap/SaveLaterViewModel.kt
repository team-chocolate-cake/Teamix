package com.chocolate.presentation.saveLater.tap

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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



    private var savedItemsList = mutableListOf(
        SavedItemUiState(
            userImageUrl = "https://example.com/user1.jpg",
            name = "John Doe",
            title = "Project A",
            description = "Working on the frontend",
            state = SavedItemState.IN_PROGRESS,
            date = "2023-08-01"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user2.jpg",
            name = "Jane Smith",
            title = "Project B",
            description = "Backend development",
            state = SavedItemState.ARCHIVED,
            date = "2023-07-25"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user3.jpg",
            name = "Michael Johnson",
            title = "Project C",
            description = "Quality Assurance",
            state = SavedItemState.COMPLETED,
            date = "2023-07-15"
        ),

        SavedItemUiState(
            userImageUrl = "https://example.com/user4.jpg",
            name = "Alice Johnson",
            title = "Project D",
            description = "Marketing strategy",
            state = SavedItemState.IN_PROGRESS,
            date = "2023-08-02"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user5.jpg",
            name = "Bob Smith",
            title = "Project E",
            description = "Data analysis",
            state = SavedItemState.ARCHIVED,
            date = "2023-07-30"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user6.jpg",
            name = "Eva Williams",
            title = "Project F",
            description = "Product design",
            state = SavedItemState.COMPLETED,
            date = "2023-07-20"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user7.jpg",
            name = "David Lee",
            title = "Project G",
            description = "Content creation",
            state = SavedItemState.IN_PROGRESS,
            date = "2023-08-05"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user8.jpg",
            name = "Sophia Brown",
            title = "Project H",
            description = "Market research",
            state = SavedItemState.ARCHIVED,
            date = "2023-07-18"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user9.jpg",
            name = "William Miller",
            title = "Project I",
            description = "Project planning",
            state = SavedItemState.COMPLETED,
            date = "2023-07-12"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user10.jpg",
            name = "Olivia Wilson",
            title = "Project J",
            description = "Prototype development",
            state = SavedItemState.IN_PROGRESS,
            date = "2023-08-08"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user11.jpg",
            name = "James Anderson",
            title = "Project K",
            description = "Client meetings",
            state = SavedItemState.ARCHIVED,
            date = "2023-07-22"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user12.jpg",
            name = "Emily Martinez",
            title = "Project L",
            description = "Report generation",
            state = SavedItemState.COMPLETED,
            date = "2023-07-10"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user13.jpg",
            name = "Daniel Clark",
            title = "Project M",
            description = "Training sessions",
            state = SavedItemState.IN_PROGRESS,
            date = "2023-08-10"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user14.jpg",
            name = "Ava Robinson",
            title = "Project N",
            description = "Social media marketing",
            state = SavedItemState.ARCHIVED,
            date = "2023-07-25"
        ),
        SavedItemUiState(
            userImageUrl = "https://example.com/user15.jpg",
            name = "Jackson Green",
            title = "Project O",
            description = "Product launch",
            state = SavedItemState.COMPLETED,
            date = "2023-07-25"
        )
    )

    init {
        initData()
    }

    private fun initData() {
        _state.update {
            it.copy(
                inProgressSavedItem = getInProgressSavedItem(),
                archivedSavedItem = getArchivedSavedItem(),
                completedSavedItem = getCompletedSavedItem(),
            )
        }
    }


    fun completeItem(savedItemUiState: SavedItemUiState) {
        Log.d("MAMO", "completeItem: MAMO")
        val currentState = _state.value
        val currentInProgressItems = currentState.inProgressSavedItem.toMutableList()
        val currentCompletedItems = currentState.completedSavedItem.toMutableList()

        val itemOfDayToUpdateIndex = currentInProgressItems.indexOfFirst { itemOfDay ->
            itemOfDay.saveItems.any {
                it.name == savedItemUiState.name &&
                        it.title == savedItemUiState.title &&
                        it.date == savedItemUiState.date &&
                        it.description == savedItemUiState.description
            }
        }

        if (itemOfDayToUpdateIndex != -1) {
            val itemOfDayToUpdate = currentInProgressItems[itemOfDayToUpdateIndex]
            val updatedSaveItems = itemOfDayToUpdate.saveItems.filterNot {
                it.name == savedItemUiState.name &&
                        it.title == savedItemUiState.title &&
                        it.date == savedItemUiState.date &&
                        it.description == savedItemUiState.description
            }

            if (updatedSaveItems.isEmpty()) {
                currentInProgressItems.removeAt(itemOfDayToUpdateIndex)
            } else {
                val updatedItemOfDay = itemOfDayToUpdate.copy(saveItems = updatedSaveItems)
                currentInProgressItems[itemOfDayToUpdateIndex] = updatedItemOfDay
            }

            val updatedItemUiState = savedItemUiState.copy(state = SavedItemState.COMPLETED)
            currentCompletedItems.add(0, SavedItemOfDay(savedItemUiState.date, listOf(updatedItemUiState))) // Add to the start of the list
            _state.update {
                it.copy(
                    inProgressSavedItem = currentInProgressItems,
                    completedSavedItem = currentCompletedItems
                )
            }
        }
    }

    private fun getInProgressSavedItem()
            : List<SavedItemOfDay> {
        return savedItemsList.filter { it.state == SavedItemState.IN_PROGRESS }.groupBy { it.date }
            .map {
                val values = it.value.map {
                    it.copy(onComplete = { completeItem(it) })
                }
                SavedItemOfDay(date = it.key, saveItems = values)
            }
    }

    private fun getArchivedSavedItem(): List<SavedItemOfDay> {
        return savedItemsList.filter { it.state == SavedItemState.ARCHIVED }
            .groupBy { it.date }
            .map {
                SavedItemOfDay(date = it.key, saveItems = it.value)
            }
    }

    private fun getCompletedSavedItem(): List<SavedItemOfDay> {
        return savedItemsList.filter { it.state == SavedItemState.COMPLETED }
            .groupBy { it.date }
            .map {
                SavedItemOfDay(date = it.key, saveItems = it.value)
            }
    }
}
