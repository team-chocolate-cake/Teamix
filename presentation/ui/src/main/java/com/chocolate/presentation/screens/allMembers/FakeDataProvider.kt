package com.chocolate.presentation.screens.allMembers

val members: List<MemberItemUiState> = listOf(
    MemberItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Ali",
        "UX/UI Designer"
    ),
    MemberItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Ali2",
        "UX/UI Designer"
    ),
    MemberItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Ali3",
        "UX/UI Designer"
    ),
)

val allMembersUiState = AllMembersUiState(
    members = members,

)