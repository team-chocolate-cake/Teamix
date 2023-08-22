package com.chocolate.presentation.screens.bottomNavigation

import com.chocolate.presentation.R

sealed class BottomNavigationItem(var title:String, var icon:Int, var screen_route:String){
    object Home:BottomNavigationItem(title = "Home" , icon = R.drawable.home  , screen_route = "Home")
    object Search:BottomNavigationItem(title = "Search" , icon =R.drawable.search  , screen_route = "search")
    object Tasks:BottomNavigationItem(title = "Tasks" , icon =R.drawable.task  , screen_route = "tasks")
    object DMs:BottomNavigationItem(title = "DMs" , icon =R.drawable.dm  , screen_route = "dMs")
    object Profile:BottomNavigationItem(title = "Profile" , icon =R.drawable.profile  , screen_route = "profile_screen")
}
