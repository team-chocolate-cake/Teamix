package com.chocolate.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Pink80 = Color(0xFFEFB8C8)
val Pink40 = Color(0xFF7D5260)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Mauve = Color(0xFF65228F)
val Green = Color(0xFF44C969)
val Yellow = Color(0xABFFEB3B)
val Orange = Color(0xFFEF882A)

val Gray = Color(0xFFBEBEBE)
val LightGray = Color(0xFFEFEFEF)
val DarkGray = Color(0xAB4D4D4D)
val Transparent = Color(0x00000000)

val LightPrimary = Color(0xFF3558C2)
val OnLightPrimary = Color(0xFFFFFFFF)
val OnLightPrimary67 = Color(0xABEFF1F5)
val LightSecondary = Color(0xFFEFF1F5)
val OnLightSecondary = Color(0xFFD2DEFF)
val OnLightSecondary67 = Color(0xABD2DEFF)
val OnLightSecondary38 = Color(0x998095D3)
val LightCard = Color(0xFFFFFFFF)
val LightBorder = Color(0xCCEAEAEA)
val LightBackground = Color(0xFFF5F5F5)
val OnLightBackground = Color(0xFFFFFFFF)
val OnLightBackground87 = Color(0xDE000000)
val OnLightBackground60 = Color(0x99121212)
val OnLightBackground38 = Color(0x61F6F6F6)
val LightStatusBar = Color(0xFF000000)
val LightRed60 = Color(0x99FF3838)
val LightRed = Color(0xFFE82424)

val DarkPrimary = Color(0xFF3558C2)
val OnDarkPrimary = Color(0xFFFFFFFF)
val OnDarkPrimary67 = Color(0xFFD2DEFF)
val DarkSecondary = Color(0xFF262626)
val OnDarkSecondary = Color(0xFF2B2B2B)
val OnDarkSecondary67 = Color(0xFF2B2B2B)
val OnDarkSecondary38 = Color(0x998095D3)
val DarkCard = Color(0xFF262626)
val DarkBorder = Color(0xFF302F2F)
val DarkBackground = Color(0xFF1F1F1F)
val OnDarkBackground = Color(0xFF262626)
val OnDarkBackground87 = Color(0xDEF6F6F6)
val OnDarkBackground60 = Color(0x99F6F6F6)
val OnDarkBackground38 = Color(0x61F6F6F6)
val DarkStatusBar = Color(0xFFFFFFFF)
val DarkRed60 = Color(0x99FF3838)
val DarkRed = Color(0xFFFF6B6B)

@Immutable
data class CustomColorsPalette(
    val primary: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val onPrimary67: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val onSecondary67: Color = Color.Unspecified,
    val onSecondary38: Color = Color.Unspecified,
    val border: Color = Color.Unspecified,
    val card: Color = Color.Unspecified,
    val statusBar: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground87: Color = Color.Unspecified,
    val onBackground60: Color = Color.Unspecified,
    val onBackground38: Color = Color.Unspecified,
    val gray: Color = Color.Unspecified,
    val lightGray: Color = Color.Unspecified,
    val darkGray: Color = Color.Unspecified,
    val yellow: Color = Color.Unspecified,
    val green: Color = Color.Unspecified,
    val mauve: Color = Color.Unspecified,
    val orange: Color = Color.Unspecified,
    val white: Color = Color.Unspecified,
    val black: Color = Color.Unspecified,
    val red: Color = Color.Unspecified,
    val red60: Color = Color.Unspecified,
    val transparent: Color = Color.Unspecified
)