package com.chocolate.presentation.composable

import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeCard(
    cardItem: @Composable () -> Unit,
    onclickDismiss: (messageId: Int) -> Unit,
) {
    val state = rememberDismissState(
        confirmValueChange = {
            when (it) {
                DismissValue.Default -> false

                DismissValue.DismissedToEnd -> {
                    onclickDismiss
                    true
                }

                DismissValue.DismissedToStart -> {
                    onclickDismiss
                    true
                }
            }
        }
    )
    SwipeToDismiss(
        state = state,
        background = { SwipeBackGround() },
        dismissContent = {
            cardItem()
        }
    )
}

@Preview
@Composable
private fun Preview() {
    SwipeCard(cardItem = {  }, onclickDismiss = {},)
}