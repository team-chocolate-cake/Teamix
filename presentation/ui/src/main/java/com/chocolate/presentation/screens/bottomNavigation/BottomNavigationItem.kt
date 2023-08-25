package com.chocolate.presentation.screens.bottomNavigation

import com.chocolate.presentation.R

sealed class BottomNavigationItem(var title:String, var icon:Int, var screenRoute:String){
    object Home:BottomNavigationItem(title = "Home" , icon = R.drawable.home  , screenRoute = "Home")
    object Search:BottomNavigationItem(title = "Search" , icon =R.drawable.search  , screenRoute = "search")
    object Tasks:BottomNavigationItem(title = "Tasks" , icon =R.drawable.task  , screenRoute = "tasks")
    object DMs:BottomNavigationItem(title = "DMs" , icon =R.drawable.dm  , screenRoute = "dMs")
    object Profile:BottomNavigationItem(title = "Profile" , icon =R.drawable.profile  , screenRoute = "profile_screen")
}
