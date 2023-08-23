package com.chocolate.presentation.screens.add_member

import com.chocolate.viewmodel.addMember.AddMemberUiState
import com.chocolate.viewmodel.addMember.SuggestedMemberItemUiState
import com.chocolate.viewmodel.allMembers.AllMembersUiState
import com.chocolate.viewmodel.allMembers.MemberItemUiState
import com.chocolate.viewmodel.messageSearch.MessageSearchUiState
import com.chocolate.viewmodel.pinnedMessages.MessageItemUiState
import com.chocolate.viewmodel.pinnedMessages.PinnedMessagesUiState

val members: List<MemberItemUiState> = listOf(
    MemberItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Alisdsfsdfsdfsfsdfsdfsdfsddfsdfsfsdfsd",
        "UX/UI Designer"
    ),
    MemberItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "A",
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


val searchMessage = MessageSearchUiState(
    messages = messages
)

val suggestedMembers: List<SuggestedMemberItemUiState> = listOf(
    SuggestedMemberItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Ali",
        "UX/UI Designer",
        true
    ),
    SuggestedMemberItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "A",
        "UX/UI Designer",
        false
    ),
    SuggestedMemberItemUiState(
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "Ali3",
        "UX/UI Designer",
        true
    ),
)

val addMemberUiState = AddMemberUiState(
    suggestedMembers = suggestedMembers,
    selectedMembers = listOf(members[0], members[1])
)