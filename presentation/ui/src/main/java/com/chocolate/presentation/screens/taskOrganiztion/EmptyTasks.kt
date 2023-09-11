package com.chocolate.presentation.screens.taskOrganiztion

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.FitImage
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors



@Composable
fun EmptyTasks(
    onClickCreateTask: () -> Unit,
) {
    val colors = MaterialTheme.customColors()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpacingXLarge),
        verticalArrangement = Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FitImage(
            modifier = Modifier
                .size(137.dp, 108.dp)
                .padding(bottom = SpacingXXLarge),
            image = painterResource(id = R.drawable.box),
            contentDescription = stringResource(R.string.welcome_image)
        )
        Text(
            modifier = Modifier.padding(bottom = SpacingXMedium),
            text = stringResource(id = R.string.no_task_found),
            style = MaterialTheme.typography.bodyMedium,
            color = colors.onBackground87
        )
        Text(
            text = stringResource(id = R.string.task_description),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = colors.onBackground60
        )

        TeamixButton(
            onClick = { onClickCreateTask() },
            colors = colors,
            modifier = Modifier
                .padding(top = SpacingExtraHuge)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.add_new_tasks),
                style = MaterialTheme.typography.bodyLarge,
                color = colors.onPrimary
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun EmptyTaskPreview() {
    TeamixTheme {
        EmptyTasks {}
    }
}
