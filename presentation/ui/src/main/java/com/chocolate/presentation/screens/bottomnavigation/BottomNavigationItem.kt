package com.chocolate.presentation.screens.bottomnavigation

import androidx.annotation.StringRes
import com.chocolate.presentation.R

sealed class BottomNavigationItem(@StringRes var title:Int, var icon:Int, var screenRoute:String){
    object Home:BottomNavigationItem(title = R.string.home , icon = R.drawable.home  , screenRoute = "Home")
    object Search:BottomNavigationItem(title = R.string.search , icon =R.drawable.search  , screenRoute = "search")
    object TasksOrganization:BottomNavigationItem(title = R.string.tasksOrganization , icon =R.drawable.task_organization  , screenRoute = "tasks_organization ")
    object DMs:BottomNavigationItem(title = R.string.dms , icon =R.drawable.dm  , screenRoute = "dMs")
    object Profile:BottomNavigationItem(title = R.string.profile , icon =R.drawable.profile  , screenRoute = "profile_screen")
}
