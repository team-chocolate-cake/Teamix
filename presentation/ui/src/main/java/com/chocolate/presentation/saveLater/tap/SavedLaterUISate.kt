package com.chocolate.presentation.saveLater.tap

data class SavedLaterUISate(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val inProgressSavedItem: List<SavedItemOfDay> = emptyList(),
    val archivedSavedItem: List<SavedItemOfDay> = emptyList(),
    val completedSavedItem: List<SavedItemOfDay> = emptyList(),
)

data class SavedItemOfDay(
    val date: String = "",
    val saveItems: List<SavedItemUiState> = emptyList()
)

data class SavedItemUiState(
    val userImageUrl: String = "",
    val name: String = "",
    val title: String = "",
    val description: String = "",
    val state: SavedItemState,
    val date: String = "",
)

enum class SavedItemState {
    IN_PROGRESS, ARCHIVED, COMPLETED
}