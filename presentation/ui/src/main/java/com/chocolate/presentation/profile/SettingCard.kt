package com.chocolate.presentation.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors

@Composable
fun SettingCard(click:()->Unit,text:String,icon:Painter) {
val color=MaterialTheme.customColors()

    Card(
        modifier = Modifier
            .clickable {click() }
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = CardDefaults.cardColors(color.card)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = icon,
                contentDescription = null, modifier = Modifier.padding(end = 8.dp)
            )

            Text(
                text = text, style = MaterialTheme.typography.bodyMedium,
                color = color.onBackground60
            )
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.alt_arrow_right),
                contentDescription = null
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingCardPreview() {
    ProfileLanguageDialog()
}
