package com.example.ipsports.View.Logo

/*una pantalla de bienvenida inicial con un logotipo.
Esta pantalla es común en aplicaciones que quieren mostrar una pantalla de carga o pantalla de bienvenida al iniciar.*/

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale


@Preview
@Composable
fun LogoScreen() {
    // Escala animada para el logo
    val scaleAnim = rememberInfiniteTransition()
    val scale by scaleAnim.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Animación de opacidad para el texto
    val alphaAnim = rememberInfiniteTransition()
    val alpha by alphaAnim.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E88E5),// Azul brillante (inicio)
                        Color(0xFF1565C0), // Azul medio
                        Color(0xFF0D47A1), // Azul más oscuro
                     //  Color(0xFF000000)  // Negro (final)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animación de escala para el logo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

