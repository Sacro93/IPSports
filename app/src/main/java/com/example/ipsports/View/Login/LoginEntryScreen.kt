package com.example.ipsports.View.Login

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.theme.Color.IpSportsTheme
import com.example.ipsports.View.theme.Font.QS

@Composable
fun LoginEntryScreen(onNavigateToLogin: () -> Unit,
                     onNavigateToRegister: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E88E5),// Azul brillante (inicio)
                        Color(0xFF1565C0), // Azul medio
                        Color(0xFF0D47A1), // Azul más oscuro
                        Color(0xFF000000)  // Negro (final)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            QS()
            // Título de la pantalla
            Text(
                text = "¡Bienvenido!",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón "Iniciar Sesión" (Usando ButtonPrimary)
            ButtonPrimary(
                text = "Iniciar Sesión",
                onClick = { onNavigateToLogin() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(280.dp) // Opcional: Puedes usar Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Registrarse"

            ButtonPrimary(
                text = "Registrarse",
                onClick = { onNavigateToRegister() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(280.dp)
            )
        }
    }
}

