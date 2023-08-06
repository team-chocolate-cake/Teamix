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
    val onComplete: () -> Unit = {},
    val onUnArchive: () -> Unit = {},
    val onArchive : () -> Unit ={},
    val moveToInProgress : () -> Unit = {},
    val removeFromLater : () -> Unit = {}

)
enum class SavedItemState {
    IN_PROGRESS, ARCHIVED, COMPLETED
}


fun List<SavedItemOfDay>.toUIState(
    onComplete: (SavedItemUiState) -> Unit = {},
    onUnArchive: (SavedItemUiState) -> Unit = {},
    onArchive: (SavedItemUiState) -> Unit = {},
    moveToInProgress: (SavedItemUiState) -> Unit = {},
    removeFromLater: (SavedItemUiState) -> Unit = {}
): List<SavedItemOfDay> = map {
    it.copy(saveItems = it.saveItems.map {
        it.copy(
            onComplete = { onComplete(it) },
            onUnArchive = { onUnArchive(it) },
            onArchive = { onArchive(it) },
            moveToInProgress = { moveToInProgress(it) },
            removeFromLater = { removeFromLater(it) }
        )
    }
    )
}
