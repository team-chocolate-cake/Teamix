package com.chocolate.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Pink80 = Color(0xFFEFB8C8)
val Pink40 = Color(0xFF7D5260)
val gray = Color(0xFFBEBEBE)
val onLightGray = Color(0xFFEFEFEF)
val onDarkGray = Color(0xAB4D4D4D)

val onLightPrimary = Color(0xFF3558C2)
val onLightBackground = Color(0xFFF5F5F5)
val onLightOnBackground87 = Color(0xDE000000)
val onLightOnBackground60 = Color(0x99121212)
val secondary = Color(0xFFF3F4F9)
val onSecondary = Color(0xFFC9C6FF)
val onPrimary = Color(0xFFFFFFFF)
val yellow = Color(0xFFEF882A)
val green = Color(0xFF44C969)
val red = Color(0xFFFF0000)
val red60 = Color(0x99FF3838)




val card = Color(0xFFFFFFFF)
val border = Color(0x145752AD)
val onDarkPrimary = Color(0xFF3558C2)
val onDarkBackground = Color(0xFF1F1F1F)
val onDarkOnBackground87 = Color(0xDEF6F6F6)
val onDarkOnBackground60 = Color(0x99F6F6F6)
val onBackground38 = Color(0x61F6F6F6)

@Immutable
data class CustomColorsPalette(
    val primary: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val border: Color = Color.Unspecified,
    val card: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground87: Color = Color.Unspecified,
    val onBackground60: Color = Color.Unspecified,
    val onBackground38: Color = Color.Unspecified,
    val gray: Color = Color.Unspecified,
    val lightGray: Color = Color.Unspecified,
    val darkGray: Color = Color.Unspecified,
    val yellow: Color = Color.Unspecified,
    val green: Color = Color.Unspecified,
    val red: Color = Color.Unspecified,
    val red60: Color = Color.Unspecified
)