package com.example.ipsports.View.Logo

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import com.example.ipsports.View.theme.Font.QS
import com.example.ipsports.data.routesNavigation.Routes
import kotlinx.coroutines.delay



@Composable
fun LogoScreen(navController: NavController? = null) {
    val transition = rememberInfiniteTransition()
    val scale by transition.animateFloat(
        initialValue = 0.7f, targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // 🔹 Esperar 3 segundos y navegar automáticamente
    LaunchedEffect(Unit) {
        delay(3000) // Espera 3 segundos antes de cambiar de pantalla
        navController?.navigate(Routes.HOME) {
            popUpTo(Routes.LOGIN_ENTRY) { inclusive = true } // Elimina pantallas previas
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E88E5),
                        Color(0xFF1565C0),
                        Color(0xFF0D47A1),
                        Color(0xFF000000)
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
            QS()
        }
    }
}
