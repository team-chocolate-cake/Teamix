package com.chocolate.presentation.screens.bottomnavigation

import androidx.annotation.StringRes
import com.chocolate.presentation.R

sealed class BottomNavigationItem(@StringRes var title:Int, var icon:Int, var screenRoute:String){
    object Home:BottomNavigationItem(title = R.string.home , icon = R.drawable.home  , screenRoute = "home")
    object Search:BottomNavigationItem(title = R.string.search , icon =R.drawable.search  , screenRoute = "search")
    object DMs:BottomNavigationItem(title = R.string.direct_messages , icon =R.drawable.dm  , screenRoute = "direct_message")
    object Profile:BottomNavigationItem(title = R.string.profile , icon =R.drawable.profile  , screenRoute = "profile")
}
