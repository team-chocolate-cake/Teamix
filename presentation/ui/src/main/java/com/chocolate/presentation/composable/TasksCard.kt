package com.chocolate.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.organizationTasks.TasksUiState


@Composable
fun TasksCard(
    state: TasksUiState
) {
    val firstSubTasksCheckedState = remember { mutableStateOf(true) }
    val secondSubTasksCheckedState = remember { mutableStateOf(true) }
    val fontStyle = MaterialTheme.typography
    val color = MaterialTheme.customColors()

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .wrapContentSize(),
        colors = CardDefaults.cardColors(color.card)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 8.dp)
        ) {
            Row {
                Icon(
                    modifier = Modifier.padding(end = 4.dp),
                    painter = painterResource(id = R.drawable.checklist_minimalistic),
                    contentDescription = null,
                    tint = color.onBackground87
                )
                Text(
                    text = state.taskName,
                    color = color.onBackground87,
                    style = fontStyle.titleMedium
                )
            }
            Text(
                state.taskDate, modifier = Modifier.align(Alignment.CenterEnd),
                color = color.onBackground60, style = fontStyle.labelSmall
            )

        }
        Divider(modifier = Modifier.padding(SpacingXMedium), color = color.border)
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = firstSubTasksCheckedState.value,
                onCheckedChange = { firstSubTasksCheckedState.value = it },
                colors = CheckboxDefaults.colors(color.primary)
            )
            Text(
                text = state.fistSubTaskName,
                color = color.onBackground87,
                style = fontStyle.labelMedium
            )
        }
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = secondSubTasksCheckedState.value,
                onCheckedChange = { secondSubTasksCheckedState.value = it },
                colors = CheckboxDefaults.colors(color.primary)
            )
            Text(
                text = state.secondSubTaskName, color = color.onBackground87,
                style = fontStyle.labelMedium
            )
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Show all Sub tasks", color = color.primary)
        }

        Row(
            modifier = Modifier
                .height(36.dp)
                .background(color.onSecondary67),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${(state.taskProgress * 100).toInt()}%",
                modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                color = color.onBackground87,
                style = fontStyle.labelMedium
            )
            Box(modifier = Modifier.padding(end = 16.dp)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(color.card)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(state.taskProgress)
                        .height(8.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(color.primary)
                )
            }
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
@Preview(showBackground = true, backgroundColor = 0)
fun TasksCardPreview() {
    TeamixTheme {
        TasksCard(TasksUiState())
    }

}