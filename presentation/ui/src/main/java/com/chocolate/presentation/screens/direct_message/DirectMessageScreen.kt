package com.chocolate.presentation.screens.direct_message

import android.annotation.SuppressLint
import androidx.annotation.DimenRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.DirectMessageChat
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.create_channel.composable.ActionSnakeBar
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.directMessage.ChatUiState
import com.chocolate.viewmodel.directMessage.DirectMessageInteractions
import com.chocolate.viewmodel.directMessage.DirectMessageUiState
import com.chocolate.viewmodel.directMessage.DirectMessageViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DirectMessageScreen(viewModel: DirectMessageViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    DirectMessageContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DirectMessageContent(state: DirectMessageUiState, interactions: DirectMessageInteractions) {
    val context = LocalContext.current
    val colors = MaterialTheme.customColors()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.customColors().primary,
        darkIcons = false
    )
    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colors.primary,
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chat),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    ) {
        Column() {
            TeamixTextField(
                modifier = Modifier.padding(SpacingXLarge),
                value = state.searchInput,
                singleLine = true,
                onValueChange = { interactions.onChangeSearchQuery(it) },
                containerColor = Color.White,
                hint = stringResource(R.string.search_for_chats),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (state.searchInput.isNotEmpty())
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = null,
                            tint = MaterialTheme.customColors().onBackground87,
                            modifier = Modifier.clickable { interactions.onClickDeleteQuery() }
                        )
                }
            )
            LazyColumn(
                modifier = Modifier.padding(horizontal = SpacingXMedium),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.chats.size) {
                    DirectMessageChat(state = state.chats[it])
                }
            }
        }
    }
}
