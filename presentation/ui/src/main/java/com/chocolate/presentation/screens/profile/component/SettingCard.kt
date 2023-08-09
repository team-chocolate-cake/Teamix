package com.chocolate.presentation.screens.profile.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CardHeight56
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors

@Composable
fun SettingCard(click: () -> Unit, text: String, icon: Painter
                ,textColor: Color =MaterialTheme.customColors().onBackground60,) {
    val color = MaterialTheme.customColors()

    Card(
        modifier = Modifier
            .clickable { click() }
            .fillMaxWidth(),

        colors = CardDefaults.cardColors(color.card)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(CardHeight56)
                .padding(horizontal = Space16),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = icon,
                contentDescription = null, modifier = Modifier.padding(end = Space8)
            )

            Text(
                text = text, style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.alt_arrow_right),
                contentDescription = null,
                tint = textColor
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingCardPreview() {
    SettingCard({}, "Test", painterResource(id = R.drawable.ownerpowers))
}
