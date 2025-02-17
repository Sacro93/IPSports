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
import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ipsports.R


@Composable
fun LogoScreen() {
    // 🔄 Animación de Lottie
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.animation1) // Ruta del JSON en res/raw/
    )
    val progress by animateLottieCompositionAsState(
        composition, iterations = LottieConstants.IterateForever
    )

    // 🔹 Animación de escala
    val scaleAnim = rememberInfiniteTransition()
    val scale by scaleAnim.animateFloat(
        initialValue = 0.8f, targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // 🔹 Animación de opacidad
    val alphaAnim = rememberInfiniteTransition()
    val alpha by alphaAnim.animateFloat(
        initialValue = 0.3f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E88E5), // Azul brillante
                        Color(0xFF1565C0), // Azul medio
                        Color(0xFF0D47A1)  // Azul oscuro
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // **Lottie Animation**
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // **Logo Estático en Círculo**
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale)
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val logo: Painter = painterResource(id = R.drawable.logo_1)
                Image(painter = logo, contentDescription = stringResource(id = R.string.app_name))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogoScreenPreview() {
    LogoScreen()
}

