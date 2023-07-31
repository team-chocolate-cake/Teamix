package com.chocolate.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.chocolate.presentation.R

val OpenSans = FontFamily(
    Font(R.font.open_sans_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.open_sans_regular, weight = FontWeight.Normal),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = textSize22,
        lineHeight = textHeight28
    ),

    titleMedium = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = textSize16,
        lineHeight = textHeight24
    ),

    titleSmall = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = textSize16,
        lineHeight = textHeight20
    ),

    bodyLarge = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = textSize16,
        lineHeight = textHeight24
    ),

    bodyMedium = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = textSize14,
        lineHeight = textHeight20
    ),

    bodySmall = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = textSize12,
        lineHeight = textHeight16
    ),

    labelLarge = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = textSize12,
        lineHeight = textHeight16
    ),

    labelMedium = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = textSize12,
        lineHeight = textHeight14
    ),

    labelSmall = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = textSize12,
        lineHeight = textHeight16
    )
)