package com.chocolate.presentation

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome_screen")
    object OnBoarding: Screen("on_boarding_screen")
    object OrganizationName: Screen("organization_name")
    object Login: Screen("login_screen")
    object Profile: Screen("profile_screen")
    object OwnerPower: Screen("owner_power_screen")
}
