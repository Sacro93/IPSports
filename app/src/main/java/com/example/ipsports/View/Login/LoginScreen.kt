package com.example.ipsports.View.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Reusable.InputField
import com.example.ipsports.ui.theme.IpSportsTheme


@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF457B9D), // Azul profundo
                        Color(0xFFA8DADC)  // Azul claro
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Espacio para un logo (vacío por ahora)
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.onSurface, shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Título
            Text("Iniciar Sesión",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para email
            InputField(
                value = "",
                onValueChange = { /* Manejar cambios en el email */ },
                label = "Correo Electrónico",
                placeholder = "Escribe tu correo...",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Correo",
                        tint = MaterialTheme.colorScheme.onPrimary // Ícono del color primario
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de texto para contraseña
            InputField(
                value = "",
                onValueChange = { /* Manejar cambios en la contraseña */ },
                label = "Contraseña",
                placeholder = "Escribe tu contraseña...",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Candado",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Iniciar Sesión" (Usando ButtonPrimary)
            ButtonPrimary(
                text = "Iniciar Sesión",
                onClick = { /* Lógica de inicio de sesión */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(280.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto para registrarse
            TextButton(onClick = {}) {
                Text(
                    "¿No tienes cuenta? Regístrate aquí.",

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    IpSportsTheme {
        LoginScreen()
    }
}
