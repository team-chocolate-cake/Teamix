package com.chocolate.presentation.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = OnDarkPrimary,
    secondary = OnSecondary,
    tertiary = Pink80,
    background = OnDarkBackground
)

private val LightColorScheme = lightColorScheme(
    primary = OnLightPrimary,
    secondary = OnSecondary,
    tertiary = Pink40,
    background = OnLightBackground
)

val onLightCustomColorsPalette = CustomColorsPalette(
    primary = OnLightPrimary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    border = Border,
    card = Card,
    background = OnLightBackground,
    onBackground87 = OnLightOnBackground87,
    onBackground60 = OnLightOnBackground60,
    onBackground38 = OnBackground38,
    gray= Gray,
    lightGray = OnLightGray,
    yellow= Yellow,
    green= Green,
    red60= Red60,
    red = Red
)

val onDarkCustomColorsPalette = CustomColorsPalette(
    primary = OnDarkPrimary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    border = Border,
    card = Card,
    background = OnDarkBackground,
    onBackground87 = OnDarkOnBackground87,
    onBackground60 = OnDarkOnBackground60,
    onBackground38 = OnBackground38,
    gray= Gray,
    darkGray = OnDarkGray,
    yellow= Yellow,
    green= Green,
    red60= Red60,
    red = Red
)

@SuppressLint("CompositionLocalNaming")
private val TeamixCustomColors = staticCompositionLocalOf { CustomColorsPalette() }

@Composable
fun TeamixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    val customColorsPalette =
        if (darkTheme) onDarkCustomColorsPalette
        else onLightCustomColorsPalette

    CompositionLocalProvider(TeamixCustomColors provides customColorsPalette) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}

@Composable
fun MaterialTheme.customColors() = TeamixCustomColors.current