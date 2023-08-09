package com.chocolate.presentation

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome_screen")
    object OnBoarding: Screen("on_boarding_screen")
    object OrganizationName: Screen("organization_name")
}
