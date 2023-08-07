package com.chocolate.presentation.saveLater.tap

class GetSavedItemsUseCase {

    private val savedItemsList = mutableListOf(
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


    fun getInProgressSavedItem()
            : List<SavedItemOfDay> {
        return savedItemsList.filter { it.state == SavedItemState.IN_PROGRESS }.groupBy { it.date }
            .map {
                SavedItemOfDay(date = it.key, saveItems = it.value)
            }
    }

    fun getArchivedSavedItem(): List<SavedItemOfDay> {
        return savedItemsList.filter { it.state == SavedItemState.ARCHIVED }
            .groupBy { it.date }
            .map {
                SavedItemOfDay(date = it.key, saveItems = it.value)
            }
    }

    fun getCompletedSavedItem(): List<SavedItemOfDay> {
        return savedItemsList.filter { it.state == SavedItemState.COMPLETED }
            .groupBy { it.date }
            .map {
                SavedItemOfDay(date = it.key, saveItems = it.value)
            }
    }


    private inline fun <T> MutableList<T>.removeIfAndTransform(index: Int, predicate: (T) -> Boolean): T? {
        return if (predicate(this[index])) removeAt(index) else null
    }

    private fun SavedItemUiState.matches(other: SavedItemUiState): Boolean =
        name == other.name && title == other.title && date == other.date && description == other.description





}