package com.chocolate.presentation.screens.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.searchRoute(){
    composable(Screen.Search.route){
        SearchScreen()
    }
}