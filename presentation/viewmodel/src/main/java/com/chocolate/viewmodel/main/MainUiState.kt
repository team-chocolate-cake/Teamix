package com.chocolate.viewmodel.main

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class MainUiState(
    val isDark: Flow<Boolean> = flowOf(false),
    val isLoggedIn: Boolean = false,
)
