package com.chocolate.viewmodel.addTask

import java.text.SimpleDateFormat
import java.util.Locale

data class AddTaskUiState(
    val name: String = "Add",

    val title: String = "",
    val startDate: DatetimeUiState = DatetimeUiState(),
    val endDate: DatetimeUiState = DatetimeUiState(),
    val membersOrganization: List<MembersOrganizationUiState> = listOf(
        MembersOrganizationUiState(
            1,
            "ameer",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpqhLZILtArVdjKO_DlYLy7OU7-mYl-oyYwHsbsPR5bQ&s",
            false
        ),
        MembersOrganizationUiState(
            1,
            "ameer",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpqhLZILtArVdjKO_DlYLy7OU7-mYl-oyYwHsbsPR5bQ&s",
            false
        ),
        MembersOrganizationUiState(
            1,
            "ameer",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpqhLZILtArVdjKO_DlYLy7OU7-mYl-oyYwHsbsPR5bQ&s",
            false
        ),
        MembersOrganizationUiState(
            1,
            "ameer",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpqhLZILtArVdjKO_DlYLy7OU7-mYl-oyYwHsbsPR5bQ&s",
            false
        ),
        MembersOrganizationUiState(
            1,
            "ameer",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpqhLZILtArVdjKO_DlYLy7OU7-mYl-oyYwHsbsPR5bQ&s",
            false
        ),
    ),
    val subTasks: List<String> = listOf(
        "Ameer", "aaaa"
    ),
    val isLoading: Boolean = false,
    val isShowDatePickerDialog: Boolean = false,
    val error: String? = null
)

data class DatetimeUiState(
    val date: String = "",
    val time: String = "",

    )

fun DatetimeUiState.toMillis(): Long {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy - h:mm a", Locale.getDefault())
    return dateFormat.parse("${this.date} - ${this.time}")?.time ?: 0
}

data class MembersOrganizationUiState(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val isSelected: Boolean = false,
)


