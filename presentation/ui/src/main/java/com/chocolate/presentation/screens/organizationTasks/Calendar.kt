package com.chocolate.presentation.screens.organizationTasks

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDate() {
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled by remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                openDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    },
                    enabled = confirmEnabled
                ) {
                    Text(stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTime() {
    var showTimePicker by remember { mutableStateOf(false) }
    val state = rememberTimePickerState()
    val snackState = remember { SnackbarHostState() }

    Box(propagateMinConstraints = false) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { showTimePicker = true }
        ) {
            Text(stringResource(R.string.set_time))
        }

        SnackbarHost(hostState = snackState)
    }

    if (showTimePicker) {
        TimePicker(
            modifier = Modifier,
            state = state,
            colors = TimePickerDefaults.colors(
                clockDialColor = MaterialTheme.colorScheme.secondaryContainer,
                clockDialSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
                clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                selectorColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                periodSelectorBorderColor = MaterialTheme.colorScheme.primary,
                periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                periodSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            layoutType = TimePickerLayoutType.Vertical
        )
    }
}