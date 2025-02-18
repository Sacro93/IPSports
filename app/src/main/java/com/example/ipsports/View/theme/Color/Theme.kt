package com.example.ipsports.View.theme.Color

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography


// Colores personalizados inspirados en la estética de las imágenes
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF007AFF),    // Azul brillante para botones principales
    onPrimary = Color.White,       // Texto blanco sobre azul
    background = Color(0xFF121212), // Fondo oscuro principal
    surface = Color(0xFF1E1E1E),    // Superficies (cards, modales)
    onSurface = Color(0xFFD9D9D9),  // Texto en superficies (gris claro)
    secondary = Color(0xFF5AC8FA), // Azul celeste para acentos
    onSecondary = Color.White,     // Texto sobre azul celeste
    error = Color(0xFFFF3B30),      // Rojo para errores
    onError = Color.White          // Texto blanco sobre rojo
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF007AFF),    // Azul brillante
    onPrimary = Color.White,       // Texto blanco sobre azul
    background = Color(0xFFF2F2F7), // Fondo claro principal
    surface = Color(0xFFFFFFFF),    // Superficies (cards, modales)
    onSurface = Color(0xFF1C1C1E),  // Texto en superficies (gris oscuro)
    secondary = Color(0xFF5AC8FA), // Azul celeste para acentos
    onSecondary = Color.White,     // Texto sobre azul celeste
    error = Color(0xFFFF3B30),      // Rojo para errores
    onError = Color.White          // Texto blanco sobre rojo
)

// Tipografía personalizada
private val AppTypography = Typography(
    bodyLarge = androidx.compose.ui.text.TextStyle(
        color = Color(0xFFD9D9D9), // Texto en blanco/gris claro
        fontSize = androidx.compose.ui.unit.TextUnit.Unspecified
    ),
    headlineMedium = androidx.compose.ui.text.TextStyle(
        color = Color.White,
        fontSize = androidx.compose.ui.unit.TextUnit.Unspecified
    )
)

@Composable
fun IpSportsTheme(
    darkTheme: Boolean = true, // Por defecto, tema oscuro
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
