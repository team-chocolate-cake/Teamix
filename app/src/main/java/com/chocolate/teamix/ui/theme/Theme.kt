package com.chocolate.teamix.ui.theme

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
    on_primary = onPrimary,
    secondary = secondary,
    on_secondary = onSecondary,
    border = border,
    card = card,
    background = onLightBackground,
    on_background_87 = onLightOnBackground87,
    on_background_60 = onLightOnBackground60,
    on_background_38 = onBackground38,
    gray = onLightGray
)

val onDarkCustomColorsPalette = CustomColorsPalette(
    primary = onDarkPrimary,
    on_primary = onPrimary,
    secondary = secondary,
    on_secondary = onSecondary,
    border = border,
    card = card,
    background = onDarkBackground,
    on_background_87 = onDarkOnBackground87,
    on_background_60 = onDarkOnBackground60,
    on_background_38 = onBackground38,
    gray = onDarkGray
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