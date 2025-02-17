package com.example.ipsports.View.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Reusable.ReusableInputField
import com.example.ipsports.View.theme.Color.IpSportsTheme
import com.example.ipsports.View.theme.Font.QuickSportsTitleGradient

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit = { _, _ -> },
    onRegisterClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        //  Color(0xFF1E88E5), // Azul brillante
                        // Color(0xFF1565C0), // Azul medio
                        Color(0xFF0D47A1), // Azul más oscuro
                        Color(0xFF000000)  // Negro
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //QuickSportsTitleWhiteWithBlackBorder()
            Spacer(modifier = Modifier.height(20.dp))

            QuickSportsTitleGradient()

            Spacer(modifier = Modifier.height(90.dp))
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp // 🔹 Tamaño más resaltado
                ),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ReusableInputField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Correo Electrónico",
                    placeholder = "Escribe tu correo...",
                    leadingIcon = Icons.Default.Email
                )

                ReusableInputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    placeholder = "Escribe tu contraseña...",
                    leadingIcon = Icons.Default.Lock,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(24.dp))


                ButtonPrimary(
                    text = "Iniciar Sesión",
                    onClick = { onLogin(email, password) },
                    modifier = Modifier
                        .width(280.dp)
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally)

                )

                Spacer(modifier = Modifier.height(50.dp))


                TextButton(onClick = onRegisterClick) {
                    Text(
                        "¿No tienes cuenta? Regístrate aquí.",
                        color = Color.White.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
    @Preview(showBackground = true)
    @Composable
    fun LoginScreenPreview() {
        IpSportsTheme {
            LoginScreen(
                onLogin = { email, password -> println("Iniciar sesión con: $email - $password") },
                onRegisterClick = { println("Ir a pantalla de registro") }
            )
        }
    }
