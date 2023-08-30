package com.chocolate.presentation.screens.organizationTasks

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TasksCard
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.home.LoadingColumn
import com.chocolate.presentation.screens.home.composable.ChannelItem
import com.chocolate.presentation.theme.LightPrimary
import com.chocolate.presentation.theme.OnLightPrimary
import com.chocolate.presentation.theme.Radius16
import com.chocolate.presentation.theme.SpacingMegaGigantic
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.organizationTasks.OrganizationTasksUiState
import com.chocolate.viewmodel.organizationTasks.OrganizationTasksViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun OrganizationTasksScreen(viewModel: OrganizationTasksViewModel= hiltViewModel()){
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()

    OrganizationTasksContent(state)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrganizationTasksContent(state: OrganizationTasksUiState) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = LightPrimary, darkIcons = false)
    TeamixScaffold(modifier = Modifier.fillMaxSize(),
        isDarkMode = isSystemInDarkTheme(),
        onLoading = { LoadingColumn() },
        title = state.organizationTitle,
        imageUrl = state.imageUrl,
        hasImageUrl = true,
        titleColor = OnLightPrimary,
        hasAppBar = true,
        floatingActionButton = {
            AnimatedVisibility(visible = state.role.lowercase() == "owner") {
                FloatingActionButton(
                    onClick = { },
                    containerColor = MaterialTheme.customColors().primary,
                    shape = RoundedCornerShape(Radius16),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = stringResource(R.string.add_fab),
                        tint = Color.White,
                    )
                }
            }
        }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = SpacingMegaGigantic),
            contentPadding = PaddingValues(vertical = SpacingXLarge),
            verticalArrangement = Arrangement.spacedBy(SpacingXMedium),
        ) {
            items(items = state.tasks, key = { currentTask ->
                currentTask.taskName
            }) {tasksUiState->
                TasksCard(tasksUiState)
            }
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OrganizationTasksPreview(){
    OrganizationTasksScreen()
}
