package com.chocolate.presentation

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome_screen")
    object OnBoarding: Screen("on_boarding_screen")
    object OrganizationName: Screen("organization_name")
    object CreateOrganization: Screen("create_organization_screen")
    object Login: Screen("login_screen")
    object Profile: Screen("profile_screen")
    object Home: Screen("Home")
    object ChooseMembers: Screen("choose_members")
    object Topic: Screen("Topic")
    object CreateChannel: Screen("create_channel")
    object SaveLater: Screen("save_later")
    object SavedTopics: Screen("saved_topics")
    object Channel: Screen("channel")
    object CreateMember: Screen("Create_member")
    object CreateTopic: Screen("Create_Topic")
    object DirectMessageChooseMember: Screen("DirectMessageChooseMember")
    object DMChat: Screen("DMChat")
}
