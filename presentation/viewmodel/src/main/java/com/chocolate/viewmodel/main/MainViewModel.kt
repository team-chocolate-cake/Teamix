package com.chocolate.viewmodel.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase
) : BaseViewModel<Boolean, Unit>(false) {
    init {
        viewModelScope.launch(Dispatchers.IO) { isDarkThem() }
    }

    suspend fun getLastSelectedAppLanguage() =
        customizeProfileSettings.getLastSelectedAppLanguage()

    suspend fun updateDarkTheme(darkTheme: Boolean, context: Context) {
        _state.update { !darkTheme }
        customizeProfileSettings.updateDarkTheme(!darkTheme)
        restart(context)
    }

    fun restart(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivities(arrayOf(intent))
    }

    private suspend fun isDarkThem() {
        _state.update { customizeProfileSettings.isDarkThem() }
    }
}