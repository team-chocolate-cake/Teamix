package com.chocolate.presentation.screens.home.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXMedium

@Composable
fun BadgeHome(number: Int, textColor: Color, cardColor: Color, modifier: Modifier = Modifier) {
    val badgeAlpha by animateFloatAsState(
        targetValue = if (number != 0) 1f else 0f,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    Card(
        modifier = modifier.wrapContentSize().alpha(badgeAlpha),
        shape = RoundedCornerShape(SpacingXLarge),
        colors = CardDefaults.cardColors(cardColor)
    ) {
        Text(
            modifier = Modifier.wrapContentSize().padding(vertical = SpacingMedium, horizontal = SpacingXMedium),
            text = number.toString(), style = MaterialTheme.typography.labelMedium,
            color = textColor, textAlign = TextAlign.End
        )
    }
}