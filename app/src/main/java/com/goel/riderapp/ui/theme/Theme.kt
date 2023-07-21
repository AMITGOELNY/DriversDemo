@file:Suppress("PrivatePropertyName")

package com.goel.riderapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColors(
    primary = blue80,
    surface = surfaceDark,
)

private val LightColorScheme = lightColors(
    primary = blue40,
    surface = surfaceLight
)

@Composable
fun RiderAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}
