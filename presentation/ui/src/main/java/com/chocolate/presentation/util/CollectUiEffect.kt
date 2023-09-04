package com.chocolate.presentation.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.utills.throttleFirst
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun CollectUiEffect(
    effect: SharedFlow<BaseViewModel.BaseUiEffect>,
    effectHandler: (effect: BaseViewModel.BaseUiEffect) -> Unit
) {
    val throttledEffect = effect.throttleFirst(1000)
    LaunchedEffect(key1 = Unit) {
        throttledEffect.collectLatest { effect ->
            effectHandler(effect)
        }
    }
}