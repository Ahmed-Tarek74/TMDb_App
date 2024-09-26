package com.core.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Black,
    secondary = lightBlue,
    tertiary = Pink,
    background = primaryDarkBlue,
    surface = primaryDarkBlue,
    onPrimary =primaryDarkBlue,
    onSecondary = lightBlue,
    error = Red,
    onErrorContainer = Pink,
    onError = lightRed
    )
private val LightColorScheme = lightColorScheme(
    primary = primaryDarkBlue,
    secondary = lightBlue,
    tertiary = Pink,
    background = primaryDarkBlue,
    surface = primaryDarkBlue,
    onPrimary =primaryDarkBlue,
    onSecondary = lightBlue,
    error = Red,
    onErrorContainer = Pink,
    onError = lightRed
)

@Composable
fun MoviesAppTheme(
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
    MaterialTheme(
        colorScheme = colorScheme,
        typography = getTypography(),
        content = content
    )
}