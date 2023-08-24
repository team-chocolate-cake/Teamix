package com.chocolate.presentation

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome_screen")
    object OnBoarding: Screen("on_boarding_screen")
    object OrganizationName: Screen("organization_name")
    object OrganizationWebView: Screen("organization_web_view_screen")
    object Login: Screen("login_screen")
    object ForgetPasswordWebView: Screen("forget_password_screen")
    object Profile: Screen("profile_screen")
    object Home: Screen("Home")
    object ChooseMembers: Screen("choose_members")
    object Topic: Screen("Topic")
    object CreateChannel: Screen("create_channel")
}
