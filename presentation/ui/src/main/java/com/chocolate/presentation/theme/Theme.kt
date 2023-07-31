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
    primary = onDarkPrimary,
    secondary = onSecondary,
    tertiary = Pink80,
    background = onDarkBackground
)

private val LightColorScheme = lightColorScheme(
    primary = onLightPrimary,
    secondary = onSecondary,
    tertiary = Pink40,
    background = onLightBackground
)

val onLightCustomColorsPalette = CustomColorsPalette(
    primary = onLightPrimary,
    onPrimary = onPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    border = border,
    card = card,
    background = onLightBackground,
    onBackground87 = onLightOnBackground87,
    onBackground60 = onLightOnBackground60,
    onBackground38 = onBackground38,
    gray= gray,
    lightGray = onLightGray,
    yellow= yellow,
    green= green,
    red60= red60,
    red = red
)

val onDarkCustomColorsPalette = CustomColorsPalette(
    primary = onDarkPrimary,
    onPrimary = onPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    border = border,
    card = card,
    background = onDarkBackground,
    onBackground87 = onDarkOnBackground87,
    onBackground60 = onDarkOnBackground60,
    onBackground38 = onBackground38,
    gray= gray,
    darkGray = onDarkGray,
    yellow= yellow,
    green= green,
    red60= red60,
    red = red
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