package com.chocolate.presentation.screens.organizationTasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.EmptyDataWithBoxLottie
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.organizationTasks.OrganizationTasksUiState

@Composable
fun EmptyTasksScreen(state: OrganizationTasksUiState, modifier: Modifier = Modifier) {
    val colors = MaterialTheme.customColors()
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            EmptyDataWithBoxLottie(
                isPlaying = true,
                isShow = state.tasks.isEmpty() && !state.isLoading,
                title = stringResource(R.string.no_tasks_found),
                subTitle = stringResource(R.string.sub_title_no_tasks)
            )
            TeamixButton(
                onClick = {},
                colors = colors,
                modifier = modifier.padding(top = SpacingExtraHuge)
            ) {
                Text(
                    text = stringResource(R.string.add_new_tasks),
                    color = MaterialTheme.customColors().onBackground60,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = SpacingXMedium),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}