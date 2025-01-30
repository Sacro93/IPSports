package com.example.ipsports.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
/*
// Paleta de colores única
private val AppColorScheme = darkColorScheme(
    primary = Color(0xFF00A8CC),    // Azul brillante
    onPrimary = Color.White,        // Texto blanco sobre azul
    background = Color(0xFF1A1A2E), // Fondo oscuro principal (azul muy oscuro)
    surface = Color(0xFF16213E),    // Fondo de tarjetas (azul oscuro)
    onSurface = Color(0xFF90A4AE),  // Texto en superficies (gris claro)
    error = Color(0xFFF44336),      // Rojo para errores
    onError = Color.White,          // Texto blanco sobre rojo
    // Colores adicionales
)



// Aplicación del tema
@Composable
fun IpSportsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme, // Esquema único
        typography = Typography,      // Tipografía
        content = content             // Contenido envuelto en el tema
    )
}
*/



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
