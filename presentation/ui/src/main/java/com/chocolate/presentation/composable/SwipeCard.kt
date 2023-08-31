package com.chocolate.presentation.composable

import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeCard(
    modifier: Modifier = Modifier,
    messageId: Int,
    onClickDismiss: (messageId: Int) -> Unit,
    cardItem: @Composable () -> Unit,
) {
    val state = rememberDismissState(
        confirmValueChange = {
            when(it){
                DismissValue.Default -> false
                DismissValue.DismissedToEnd -> false
                DismissValue.DismissedToStart -> {
                    onClickDismiss(messageId)
                    true
                }
            }
        },
    )
    SwipeToDismiss(
        modifier = modifier,
        state = state,
        background = { SwipeBackground() },
        dismissContent = {
            cardItem()
        },
        directions = setOf(DismissDirection.EndToStart)
    )
}

@Preview
@Composable
private fun Preview() {
    SwipeCard(cardItem = {  }, onClickDismiss = {}, messageId = 0)
}