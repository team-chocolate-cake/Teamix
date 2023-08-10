package com.chocolate.presentation.screens.pinned_messages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.PersonCardWithDetails
import com.chocolate.presentation.screens.pinnedMessagesUiState
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.pinnedMessages.PinnedMessagesUiState

@Composable
fun PinnedMessageScreen(
    //navController: NavController,
) {
    PinnedMessageContent(pinnedMessagesUiState)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun PinnedMessageContent(
    state: PinnedMessagesUiState

) {
    val colors = MaterialTheme.customColors()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)
                .padding(Space16),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Space8),
                contentPadding = PaddingValues(vertical = Space16)
            ) {
                items(state.messages.count()) { index ->
                    val currentItem = state.messages[index]
                    val dismissState = rememberDismissState(
                        confirmValueChange = {
                            //todo: remove currentItem
                            true
                        }
                    )
                    AnimatedVisibility(
                        visible = !dismissState.isDismissed(DismissDirection.EndToStart),
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut()
                    ){
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        background = { SwipeBackground() },
                        dismissContent = {
                            PersonCardWithDetails(
                                personImageUrl = currentItem.imageUrl,
                                title = currentItem.name,
                                subTitle = currentItem.messageContent,
                                date = currentItem.date
                            )
                        })
                }


                }
            }
        }
    }

@Composable
private fun SwipeBackground() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.customColors().red, shape = RoundedCornerShape(Radius12)
            )
            .padding(Space16),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_remove),
            tint = MaterialTheme.customColors().card,
            contentDescription = null
        )
    }
}