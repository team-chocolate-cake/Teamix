package com.chocolate.presentation.screens

import com.chocolate.presentation.screens.allMembers.AllMembersUiState
import com.chocolate.presentation.screens.allMembers.MemberItemUiState
import com.chocolate.presentation.screens.pinnedMessages.MessageItemUiState
import com.chocolate.presentation.screens.pinnedMessages.PinnedMessagesUiState

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

private val messages: List<MessageItemUiState> = listOf(
    MessageItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Ali Ahmed1",
        "Good Things Take Time A few men",
        "May 15"
    ),

    MessageItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Ali Ahmed2",
        "dark tunnels on either side of Odéo, Good Things Take Time A few men, Good Things Take Time A few men, Good Things Take Time A few men",
        "May 10"
    ),

    MessageItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Ali Ahmed3",
        "dark tunnels on either side of Odéo, Good Things Take Time A few men, Good Things Take Time A few men, Good Things Take Time A few men",
        "May 7"
    ),

    )

val pinnedMessagesUiState = PinnedMessagesUiState(
    messages = messages
)