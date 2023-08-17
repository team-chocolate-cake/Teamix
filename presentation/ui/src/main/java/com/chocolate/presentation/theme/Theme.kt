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
    secondary = DarkSecondary,
    tertiary = Pink80,
    background = OnDarkBackground
)

private val LightColorScheme = lightColorScheme(
    primary = OnLightPrimary,
    secondary = LightSecondary,
    tertiary = Pink40,
    background = OnLightBackground
)

val onLightCustomColorsPalette = CustomColorsPalette(
    primary = LightPrimary,
    onPrimary = OnLightPrimary,
    onPrimary67 = OnLightPrimary67,
    secondary = LightSecondary,
    onSecondary = OnLightSecondary,
    onSecondary67 = OnLightSecondary67,
    onSecondary38 = OnLightSecondary38,
    border = LightBorder,
    card = LightCard,
    statusBar = LightStatusBar,
    background = LightBackground,
    onBackground87 = OnLightBackground87,
    onBackground60 = OnLightBackground60,
    onBackground38 = OnLightBackground38,
    gray = Gray,
    lightGray = LightGray,
    darkGray = DarkGray,
    yellow = Yellow,
    green = Green,
    orange = Orange,
    mauve = Mauve,
    white = White,
    black = Black,
    red60 = LightRed60,
    red = LightRed
)

val onDarkCustomColorsPalette = CustomColorsPalette(
    primary = DarkPrimary,
    onPrimary = OnDarkPrimary,
    onPrimary67 = OnDarkPrimary67,
    secondary = DarkSecondary,
    onSecondary = OnDarkSecondary,
    onSecondary67 = OnDarkSecondary67,
    onSecondary38 = OnDarkSecondary38,
    border = DarkBorder,
    card = DarkCard,
    statusBar = DarkStatusBar,
    background = DarkBackground,
    onBackground87 = OnDarkBackground87,
    onBackground60 = OnDarkBackground60,
    onBackground38 = OnDarkBackground38,
    gray = Gray,
    lightGray = LightGray,
    darkGray = DarkGray,
    yellow = Yellow,
    green = Green,
    orange = Orange,
    mauve = Mauve,
    white = White,
    black = Black,
    red60 = DarkRed60,
    red = DarkRed
)

@SuppressLint("CompositionLocalNaming")
private val TeamixCustomColors = staticCompositionLocalOf { CustomColorsPalette() }

@Composable
fun TeamixTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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