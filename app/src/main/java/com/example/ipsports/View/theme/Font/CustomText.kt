package com.example.ipsports.View.theme.Font

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ipsports.R

@Composable
fun QuickSportsTitleBlue() {
    val offset = 2.dp // 🔹 Tamaño del borde
    val fontSize = 48.sp

    Box {
        // 🔹 Capa del borde blanco
        Text(
            text = "Quick Sports",
            style = TextStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.blackopsoneregular)), // Fuente personalizada
                color = Color.Black // Borde en blanco
            ),
            modifier = Modifier
                .offset(x = offset, y = offset)
        )

        // 🔹 Capa del texto principal en azul oscuro
        Text(
            text = "Quick Sports",
            style = TextStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.blackopsoneregular)), // Fuente personalizada
                color = Color(0xFF0D47A1) // Azul oscuro
            )
        )
    }
}
@Composable
fun QuickSportsTitleWhiteWithBlackBorder() {
    val offset = 2.dp // 🔹 Grosor del borde
    val fontSize = 48.sp

    Box {
        // 🔹 Borde negro (se repite el texto en distintas posiciones para formar el borde)
        for (dx in listOf(-offset, offset)) {
            for (dy in listOf(-offset, offset)) {
                Text(
                    text = "Quick Sports",
                    style = TextStyle(
                        fontSize = fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.blackopsoneregular)),
                        color = Color.Black
                    ),
                    modifier = Modifier.offset(x = dx, y = dy)
                )
            }
        }

        // 🔹 Texto blanco principal
        Text(
            text = "Quick Sports",
            style = TextStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.blackopsoneregular)),
                color = Color.White // 🔹 Cambia el color a blanco para mayor contraste
            )
        )
    }
}


@Composable
fun QuickSportsTitleGradient() {
    val offset = 2.dp // 🔹 Grosor del borde
    val fontSize = 48.sp

    // 🔹 Gradiente Azul (de claro a oscuro)
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF64B5F6), // Azul Claro
            Color(0xFF1E88E5), // Azul Brillante
            Color(0xFF0D47A1)  // Azul Oscuro
        )
    )

    Box {
        // 🔹 Borde Negro (se repite el texto en varias posiciones)
        for (dx in listOf(-offset, offset)) {
            for (dy in listOf(-offset, offset)) {
                Text(
                    text = "Quick Sports",
                    style = TextStyle(
                        fontSize = fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.blackopsoneregular)),
                        color = Color.Black
                    ),
                    modifier = Modifier.offset(x = dx, y = dy)
                )
            }
        }

        // 🔹 Texto Principal con Gradiente Azul
        Text(
            text = "Quick Sports",
            style = TextStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.blackopsoneregular)),
                brush = gradientBrush // 🔹 Aplica el gradiente
            )
        )
    }
}

@Composable
fun QS() {
    val offset = 2.dp // 🔹 Grosor del borde
    val fontSize = 48.sp

    // 🔹 Gradiente Azul (de claro a oscuro)
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF64B5F6), // Azul Claro
            Color(0xFF1E88E5), // Azul Brillante
            Color(0xFF0D47A1)  // Azul Oscuro
        )
    )

    Box {
        // 🔹 Borde Negro (se repite el texto en varias posiciones)
        for (dx in listOf(-offset, offset)) {
            for (dy in listOf(-offset, offset)) {
                Text(
                    text = "QS",
                    style = TextStyle(
                        fontSize = fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.blackopsoneregular)),
                        color = Color.Black
                    ),
                    modifier = Modifier.offset(x = dx, y = dy)
                )
            }
        }

        // 🔹 Texto Principal con Gradiente Azul
        Text(
            text = "QS",
            style = TextStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.blackopsoneregular)),
                brush = gradientBrush // 🔹 Aplica el gradiente
            )
        )
    }
}