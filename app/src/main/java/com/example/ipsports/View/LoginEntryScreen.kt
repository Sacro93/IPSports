package com.example.ipsports.View

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
import androidx.compose.ui.unit.sp
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.ui.theme.IpSportsTheme

@Composable
fun LoginEntryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                       // Color(0xFFFF7EB3), // Rosa vibrante
                       // Color(0xFFFDFFB6)  // Amarillo pastel cálido

                            //   Color(0xFF8EC5FC), // Azul pastel suave
                      //  Color(0xFFE0C3FC)  // Lavanda clara

                                Color(0xFF457B9D), // Azul profundo
                       Color(0xFFA8DADC)  // Azul claro
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
                onClick = { /* Lógica de inicio de sesión */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(280.dp) // Opcional: Puedes usar Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Registrarse"

            ButtonPrimary(
                text = "Registrarse",
                onClick = { /* Navegar a SignUpScreen */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(280.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginEntryScreenPreview() { // Cambia el nombre de la preview
    IpSportsTheme {
        LoginEntryScreen() // Llama al composable principal
    }
}