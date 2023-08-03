package com.chocolate.presentation.screens.home.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space8

@Composable
fun Badge(number: Int, textColor: Color, cardColor: Color, modifier: Modifier = Modifier){
    AnimatedVisibility(visible = (number != 0)) {
        Card(
            modifier = modifier.wrapContentSize(),
            shape = RoundedCornerShape(Space16),
            colors = CardDefaults.cardColors(cardColor)
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = Space4, horizontal = Space8),
                text = number.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = textColor,
                textAlign = TextAlign.End
            )
        }
    }
}