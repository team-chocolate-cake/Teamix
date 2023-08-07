package com.chocolate.presentation.profile


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileLanguageDialog() {
    val language = listOf("english", "arabic", "franch","ispanish")
    var (checkStatus,selected) = language.map {
        remember {
            mutableStateOf(language[1])
        }
    }
    Column(
        modifier = Modifier
            .size(width = 300.dp, height = 350.dp)
            .clip(RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        language.forEach { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = text)
                RadioButton(selected = (text==selected.value), onClick = {
                    selected.value=text
                })

            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ProfileLanguageDialogPreview() {
    ProfileLanguageDialog()
}
