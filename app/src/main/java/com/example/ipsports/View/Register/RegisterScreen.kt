package com.example.ipsports.View.Register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Reusable.ReusableInputField
import com.example.ipsports.View.theme.Color.IpSportsTheme
import com.example.ipsports.View.theme.Font.QS


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegister: (String, String, String, String, String, String) -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Spacer(modifier = Modifier.height(30.dp))

            TopAppBar(

                title = { Text("Registro", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1E88E5), // Azul brillante
                            Color(0xFF1565C0), // Azul medio
                            Color(0xFF000000)  // Negro
                        )
                    )
                )
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // 📌 **Logo**
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
             ) {
                    QS()
                }

                Spacer(modifier = Modifier.height(5.dp))


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        ReusableInputField(
                            label = "Nombre",
                            value = name,
                            onValueChange = { name = it },
                            isPassword = false,
                            leadingIcon = Icons.Default.Person
                        )
                        ReusableInputField(
                            label = "Apellido",
                            value = surname,
                            onValueChange = { surname = it },
                            isPassword = false,
                            leadingIcon = Icons.Default.Person
                        )
                        ReusableInputField(
                            label = "Correo Electrónico",
                            value = email,
                            onValueChange = { email = it },
                            isPassword = false,
                            leadingIcon = Icons.Default.Email
                        )
                        ReusableInputField(
                            label = "Contraseña",
                            value = password,
                            onValueChange = { password = it },
                            isPassword = true
                        )
                        ReusableInputField(
                            label = "Confirmar Contraseña",
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            isPassword = true
                        )
                        ReusableInputField(
                            label = "Localidad",
                            value = location,
                            onValueChange = { location = it },
                            isPassword = false,
                            leadingIcon = Icons.Default.LocationOn
                        )
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                ButtonPrimary(
                    text = "Registrarse",
                    onClick = { onRegister(name, surname, email, password, confirmPassword, location) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(250.dp),
                )


            }
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