package com.chocolate.presentation.screens.add_task.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialog(
    state: DatePickerState,
    onClickCancel: () -> Unit,
    onClickConfirm: (dateTime: Long?) -> Unit,
) {
    val colors = MaterialTheme.customColors()
    val textStyle = MaterialTheme.typography
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.5F))
    ) {
        DatePickerDialog(

            onDismissRequest = onClickCancel,
            confirmButton = {
                TextButton(
                    onClick = {
                        onClickConfirm(state.selectedDateMillis)
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        color = colors.primary,
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onClickCancel,
                ) {
                    Text(
                        text = stringResource(id = R.string.cancle),
                        color = colors.primary,
                    )
                }
            },
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = colors.primary
            ),
        ) {

            DatePicker(
                state = state,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = colors.primary
                )
            )
        }

    }
}