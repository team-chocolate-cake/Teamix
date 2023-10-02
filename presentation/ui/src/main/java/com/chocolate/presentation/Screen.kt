package com.chocolate.presentation

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome")
    object OnBoarding: Screen("on_boarding")
    object OrganizationName: Screen("organization_name")
    object CreateOrganization: Screen("create_organization")
    object Login: Screen("login")
    object Profile: Screen("profile")
    object Home: Screen("home")
    object ChooseMembers: Screen("choose_members")
    object Topic: Screen("topic")
    object CreateChannel: Screen("create_channel")
    object SavedMessage: Screen("saved_message")
    object SavedTopics: Screen("saved_topics")
    object Channel: Screen("channel")
    object CreateMember: Screen("create_member")
    object CreateTopic: Screen("create_topic")
    object DirectMessage: Screen("direct_message")
    object DirectMessageChooseMember: Screen("direct_message_choose_member")
    object DirectMessageChat: Screen("direct_message_chat")
    object Search: Screen("search")
}
