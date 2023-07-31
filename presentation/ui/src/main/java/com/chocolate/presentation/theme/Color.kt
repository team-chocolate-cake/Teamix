package com.chocolate.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Pink80 = Color(0xFFEFB8C8)
val Pink40 = Color(0xFF7D5260)
val Gray = Color(0xFFBEBEBE)
val OnLightGray = Color(0xFFEFEFEF)
val OnDarkGray = Color(0xAB4D4D4D)

val OnLightPrimary = Color(0xFF3558C2)
val OnLightBackground = Color(0xFFF5F5F5)
val OnLightOnBackground87 = Color(0xDE000000)
val OnLightOnBackground60 = Color(0x99121212)
val Secondary = Color(0xFFF3F4F9)
val OnSecondary = Color(0xFFC9C6FF)
val OnPrimary = Color(0xFFFFFFFF)
val Yellow = Color(0xFFEF882A)
val Green = Color(0xFF44C969)
val Red = Color(0xFFFF0000)
val Red60 = Color(0x99FF3838)




val Card = Color(0xFFFFFFFF)
val Border = Color(0x145752AD)
val OnDarkPrimary = Color(0xFF3558C2)
val OnDarkBackground = Color(0xFF1F1F1F)
val OnDarkOnBackground87 = Color(0xDEF6F6F6)
val OnDarkOnBackground60 = Color(0x99F6F6F6)
val OnBackground38 = Color(0x61F6F6F6)

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