package com.chocolate.presentation.screens.add_task.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.chocolate.presentation.theme.Radius8
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.addTask.DatetimeUiState

@Composable
fun DateTime(
    modifier: Modifier = Modifier,
    state: DatetimeUiState,
    title: String,
    onClick: () -> Unit,
) {
    val colors = MaterialTheme.customColors()
    val textStyle = MaterialTheme.typography

    Column(
        modifier = modifier.fillMaxSize()

    ) {
        Text(
            modifier = Modifier.padding(bottom = SpacingXMedium),
            text = title,
            style = textStyle.labelMedium,
            color = colors.onBackground87,
            textAlign = TextAlign.Start
        )
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(Radius8))
                .clickable(
                    onClick = onClick
                ),
            shape = RoundedCornerShape(Radius8)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = SpacingXMedium,
                            vertical = SpacingXLarge,
                        ),
                    text = "${state.date} - ${state.time}",
                    style = textStyle.bodyMedium,
                    color = colors.onBackground60,
                    textAlign = TextAlign.Start
                )
                Icon(

                    Icons.Outlined.DateRange,
                    title,
                    modifier = Modifier.padding(
                        end = SpacingXMedium,
                    ),
                )
            }
        }

    }
}