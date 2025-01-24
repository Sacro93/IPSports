package com.example.ipsports.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
