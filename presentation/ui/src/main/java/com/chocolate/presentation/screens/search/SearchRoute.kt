package com.chocolate.presentation.screens.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.bottomnavigation.BottomNavigationItem

fun NavGraphBuilder.searchRoute(){
    composable(BottomNavigationItem.Search.screenRoute){
        SearchScreen()
    }
}