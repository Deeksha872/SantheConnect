package com.mindmatrix.santheconnect.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🌿 Light Theme (Travel / Nature inspired)
private val LightColors = lightColorScheme(
    primary = Color(0xFF2E7D32),       // Green
    onPrimary = Color.White,

    secondary = Color(0xFFFFA726),     // Warm Orange
    onSecondary = Color.Black,

    tertiary = Color(0xFF42A5F5),      // Blue accent

    background = Color(0xFFFFF8E8),    // Soft cream
    onBackground = Color(0xFF1B1B1B),

    surface = Color.White,
    onSurface = Color.Black,

    surfaceVariant = Color(0xFFE8F5E9),
    outline = Color(0xFFBDBDBD)
)

// 🌙 Dark Theme
private val DarkColors = darkColorScheme(
    primary = Color(0xFF81C784),
    onPrimary = Color.Black,

    secondary = Color(0xFFFFB74D),
    onSecondary = Color.Black,

    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),

    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),

    surfaceVariant = Color(0xFF2C2C2C),
    outline = Color(0xFF444444)
)

@Composable
fun SantheConnectTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
        typography = Typography(),
        content = content
    )
}