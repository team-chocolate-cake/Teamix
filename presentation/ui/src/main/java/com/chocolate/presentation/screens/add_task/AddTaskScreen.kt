package com.chocolate.presentation.screens.add_task

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.add_task.composable.DateDialog
import com.chocolate.presentation.screens.add_task.composable.DateTime
import com.chocolate.presentation.screens.add_task.composable.MembersOrganization
import com.chocolate.presentation.screens.add_task.composable.TimePickerDialog
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.addTask.AddTaskScreenInteraction
import com.chocolate.viewmodel.addTask.AddTaskUiState
import com.chocolate.viewmodel.addTask.AddTaskViewModel
import com.chocolate.viewmodel.addTask.toMillis
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    addTaskViewModel: AddTaskViewModel = hiltViewModel(),
    navController: NavController = LocalNavController.current
) {
    val state by addTaskViewModel.state.collectAsState()
    val stateDateState = rememberDatePickerState(
        initialSelectedDateMillis = state.startDate.toMillis()
    )
    CollectUiEffect(addTaskViewModel.effect) { channelUiEffect ->
        when (channelUiEffect) {

        }
    }

    AddTaskContent(
        state = state,
        scrollState = rememberScrollState(),
        stateDateState = stateDateState,
        interaction = addTaskViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun AddTaskContent(
    state: AddTaskUiState, scrollState: ScrollState,
    stateDateState: DatePickerState,
    interaction: AddTaskScreenInteraction
) {
    val colors = MaterialTheme.customColors()
    val textStyle = MaterialTheme.typography
    TeamixScaffold(
        hasBackArrow = true,
        title = stringResource(id = R.string.add_task),
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .padding(SpacingXLarge)
        ) {
            Text(
                modifier = Modifier.padding(bottom = SpacingXMedium),
                text = stringResource(id = R.string.channel_name),
                style = textStyle.labelMedium,
                color = colors.onBackground87,
                textAlign = TextAlign.Start
            )

            TeamixTextField(value = state.title, onValueChange = { interaction.onChangeTitle(it) })



            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = SpacingXLarge)
            ) {

                DateTime(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    title = stringResource(id = R.string.start_date),
                    state = state.startDate,
                    onClick = interaction::onCLickStartDate

                )

                DateTime(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    title = stringResource(id = R.string.end_date),
                    state = state.endDate,
                    onClick = interaction::onCLickStartDate


                )

            }

            Text(
                modifier = Modifier.padding(bottom = SpacingXMedium),
                text = stringResource(id = R.string.add_sub_task),
                style = textStyle.labelMedium,
                color = colors.onBackground87,
                textAlign = TextAlign.Start
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TeamixTextField(
                    modifier = Modifier.weight(0.7F),
                    value = state.title,
                    onValueChange = { interaction.onChangeTitle(it) }
                )

                TeamixButton(
                    onClick = { },
                    colors = colors,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = SpacingXMedium)

                ) {
                    Text(
                        text = stringResource(R.string.add),
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.onPrimary
                    )
                }
            }

            MembersOrganization(
                state = state.membersOrganization,
                onclickAssignTask = interaction::onclickAssignTask
            )

        }
    }


    if (state.isShowDatePickerDialog) {
        DateDialog(
            stateDateState,
            interaction::onCLickCancelDatePickerDialog,
            interaction::onCLickConfirmDatePickerDialog,
        )
    }


}

@Composable
@Preview(showSystemUi = true)
fun ChannelScreenPreview() {
    TeamixTheme {
        AddTaskScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
fun TimePickerSample() {
    var showTimePicker by remember { mutableStateOf(false) }
    val state = rememberTimePickerState()
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    Box(propagateMinConstraints = false) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { showTimePicker = true }
        ) {
            Text("Set Time")
        }
        SnackbarHost(hostState = snackState)
    }

    if (showTimePicker) {
        TimePickerDialog(
            onCancel = { showTimePicker = false },
            onConfirm = {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, state.hour)
                cal.set(Calendar.MINUTE, state.minute)
                cal.isLenient = false
                snackScope.launch {
                    snackState.showSnackbar("Entered time: ${formatter.format(cal.time)}")
                }
                showTimePicker = false
            },
        ) {
            TimePicker(state = state)
        }

    }
}
