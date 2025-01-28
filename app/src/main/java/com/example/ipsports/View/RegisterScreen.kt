package com.example.ipsports.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.ipsports.ui.theme.IpSportsTheme
import com.example.ipsports.View.Reusable.InputField
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.ui.theme.White

@Composable
fun RegisterScreen(
    onRegister: (String, String, String, String, String, String) -> Unit, // Acción para registrar
    onBack: () -> Unit // Acción para volver a la pantalla anterior
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
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
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            // Título de la pantalla
            Text(
                text = "Registro",
                style = MaterialTheme.typography.headlineMedium,
                color = White
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Input para Nombre
            InputField(
                value = name,
                onValueChange = { name = it },
                label = "Nombre",
                placeholder = "Ingrese su nombre",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "nombre",
                        tint = MaterialTheme.colorScheme.onPrimary // Ícono del color primario
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Input para Apellido
            InputField(
                value = surname,
                onValueChange = { surname = it },
                label = "Apellido",
                placeholder = "Ingrese su apellido",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "nombre",
                        tint = MaterialTheme.colorScheme.onPrimary // Ícono del color primario
                    )
                })

            Spacer(modifier = Modifier.height(16.dp))

            // Input para Correo
            InputField(
                value = email,
                onValueChange = { email = it },
                label = "Correo Electrónico",
                placeholder = "Ingrese su correo",
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = "Correo Electrónico",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input para Contraseña
            InputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = " Contraseña",
                placeholder = "Repita su contraseña",
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "Confirmar Contraseña",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                visualTransformation = PasswordVisualTransformation() // Ocultar texto
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input para Confirmar Contraseña
            InputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirmar Contraseña",
                placeholder = "Repita su contraseña",
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "Confirmar Contraseña",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input para Localidad
            InputField(
                value = location,
                onValueChange = { location = it },
                label = "Localidad",
                placeholder = "Ingrese su localidad",
                leadingIcon = {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Localidad",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Registro
            ButtonPrimary(
                text = "Registrarse",
                onClick = {
                    // Llamar al callback para registrar
                    onRegister(name, surname, email, password, confirmPassword, location)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonPrimary(
                text = "Volver",
                onClick = {

                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    IpSportsTheme {
        RegisterScreen(
            onRegister = { name, surname, email, password, confirmPassword, location ->
                println("Registro exitoso con: $name, $surname, $email, $password, $confirmPassword, $location")
            },
            onBack = { println("Volver atrás") }
        )
    }
}
