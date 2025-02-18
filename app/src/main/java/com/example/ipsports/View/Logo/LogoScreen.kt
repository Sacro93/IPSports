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
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.ipsports.Model.RoutesNavigation.Routes
import com.example.ipsports.R
import com.example.ipsports.View.theme.Font.QS
import kotlinx.coroutines.delay

@Composable
fun LogoScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000) // Esperar 3 segundos
        navController.navigate(Routes.LOGIN_ENTRY) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    val infiniteTransition = rememberInfiniteTransition()

    // 🔹 Animación de escala (efecto de rebote)
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f, targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
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
                        Color(0xFF0D47A1) , // Azul oscuro
                                Color(0xFF000000)  // Negro (final)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .scale(scale),

            contentAlignment = Alignment.Center
        ) {
            QS(

            )
        }
    }
}
