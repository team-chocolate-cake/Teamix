package com.chocolate.presentation.profile.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.Space12
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8

@Composable
fun ChangeThemeDialog() {
    val textColors = listOf("Blue", "Orange", "Mauve", "Green", "Red")
    val colors = listOf(
        Color.Blue, Color.Yellow,
        Color.Magenta, Color.Green, Color.Red
    )
    val (selected) = textColors.map {
        remember {
            mutableStateOf(textColors[1])
        }
    }

    Column(
        modifier = Modifier
            .size(width = 300.dp, height = 350.dp)
            .clip(RoundedCornerShape(Space12)),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        textColors.forEach { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Space16),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(colors[textColors.indexOf(text)])
                )
                Text(text = text, modifier = Modifier.padding(start =Space8))
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(selected = (text == selected.value), onClick = {
                    selected.value = text
                })
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ChangeThemeDialogPreview() {
    ChangeThemeDialog()
}
