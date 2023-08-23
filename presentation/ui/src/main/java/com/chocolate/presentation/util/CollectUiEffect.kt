package com.chocolate.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.chocolate.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun <STATE : BaseViewModel.BaseUiState, EFFECT : BaseViewModel.BaseUiEffect> CollectUiEffect(
    viewModel: BaseViewModel<STATE, EFFECT>,
    effectHandler: (EFFECT) -> Unit
) {
    val effectFlow = viewModel.effect

    LaunchedEffect(key1 = Unit) {
        effectFlow.collectLatest { effect ->
            effectHandler(effect)
        }
    }
}