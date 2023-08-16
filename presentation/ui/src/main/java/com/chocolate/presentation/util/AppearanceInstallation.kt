package com.chocolate.presentation.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.viewmodel.main.MainViewModel
import java.util.Locale

@Composable
fun installSavedAppLanguage(context: Context){
    val userSettingsViewModel: MainViewModel = hiltViewModel()
    LaunchedEffect(key1 = Unit){
        val language = userSettingsViewModel.getLastSelectedAppLanguage()
        updateResources(context = context, localeLanguage = Locale(language))
    }
}