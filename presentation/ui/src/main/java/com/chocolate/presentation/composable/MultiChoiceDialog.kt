package com.chocolate.presentation.composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.chocolate.presentation.theme.ButtonSize48
import com.chocolate.presentation.theme.RadioButtonsHeight350
import com.chocolate.presentation.theme.Width300
import com.chocolate.presentation.theme.SpacingXXMedium
import com.chocolate.presentation.theme.SpacingXXHuge
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.customColors

@Composable
fun MultiChoiceDialog(
    onClickDone: (choice: String) -> Unit,
    onDismissRequest: () -> Unit = {},
    choices: List<String>,
    oldSelectedChoice: String,
) {
    val (selected) = choices.map { rememberSaveable { mutableStateOf(oldSelectedChoice) } }
    val color = MaterialTheme.customColors()
    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(SpacingXXHuge))
                .size(width = Width300, height = RadioButtonsHeight350)
                .background(color.card)
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                choices.forEach { text ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SpacingXLarge),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium,
                            color = color.onBackground87
                        )
                        RadioButton(
                            selected = text == selected.value,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = color.primary,
                                unselectedColor = color.onBackground60
                            ),
                            onClick = {
                                selected.value = text
                            })
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=16.dp)
                        .height(ButtonSize48)
                        .align(Alignment.End),
                    shape = RoundedCornerShape(SpacingXXMedium),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color.primary,
                        contentColor = color.onPrimary
                    ),
                    onClick = {
                        onClickDone(selected.value)
                    }
                ) {
                    Text(
                        text = "stringResource(id = R.string.done)",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiChoiceDialogPreview() {
    MultiChoiceDialog({}, {}, emptyList(), "")
}
