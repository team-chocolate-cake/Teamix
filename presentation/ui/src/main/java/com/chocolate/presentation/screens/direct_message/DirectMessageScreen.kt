package com.chocolate.presentation.screens.direct_message

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.DirectMessageChat
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.chooseMember.navigateToChooseMember
import com.chocolate.presentation.screens.direct_message_member.navigateToDMChooseMember
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.directMessage.DirectMessageInteractions
import com.chocolate.viewmodel.directMessage.DirectMessageUiEffect
import com.chocolate.viewmodel.directMessage.DirectMessageUiState
import com.chocolate.viewmodel.directMessage.DirectMessageViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DirectMessageScreen(dmViewModel: DirectMessageViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by dmViewModel.state.collectAsState()
    LaunchedEffect(key1 = dmViewModel.effect ){
        dmViewModel.effect.collectLatest {
            when(it){
                DirectMessageUiEffect.NavigateToChooseMember-> navController.navigateToDMChooseMember()

            }
        }
    }
    DirectMessageContent(state, dmViewModel)
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
                onClick = interactions::onClickNewChat
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
