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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ipsports.Model.Firebase.AuthResult
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Reusable.ReusableInputField
import com.example.ipsports.View.theme.Font.QS
import com.example.ipsports.ViewModel.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    //  Mapa para manejar los errores por campo
    var errorMessages by remember { mutableStateOf<Map<String, String>>(emptyMap()) }

    //  Observar cambios en la autenticación
    val authResult by authViewModel.authResult.observeAsState()

    LaunchedEffect(authResult) {
        when (authResult) {
            is AuthResult.Failure -> {
                val error = (authResult as AuthResult.Failure).exception.message ?: "Ocurrió un error inesperado"
                errorMessages = when {
                    "correo" in error.lowercase() -> errorMessages + ("email" to error)
                    "contraseña" in error.lowercase() -> errorMessages + ("password" to error)
                    "coinciden" in error.lowercase() -> errorMessages + ("confirmPassword" to error)
                    "obligatorio" in error.lowercase() -> errorMessages + ("general" to error)
                    else -> errorMessages + ("general" to error)
                }
            }
            else -> Unit // Si es Loading o Success, no actualizamos errores
        }
    }

    Scaffold(
        topBar = {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // 📌 **Logo**
                QS()

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        ReusableInputField(
                            label = "Nombre",
                            value = name,
                            onValueChange = {
                                name = it
                                errorMessages = errorMessages - "name" // Borra el error si cambia el input
                            },
                            leadingIcon = Icons.Default.Person
                        )
                        if (errorMessages.containsKey("name")) {
                            Text(errorMessages["name"] ?: "", color = Color.Red, fontSize = 12.sp)
                        }

                        ReusableInputField(
                            label = "Apellido",
                            value = surname,
                            onValueChange = {
                                surname = it
                                errorMessages = errorMessages - "surname"
                            },
                            leadingIcon = Icons.Default.Person
                        )
                        if (errorMessages.containsKey("surname")) {
                            Text(errorMessages["surname"] ?: "", color = Color.Red, fontSize = 12.sp)
                        }

                        ReusableInputField(
                            label = "Correo Electrónico",
                            value = email,
                            onValueChange = {
                                email = it
                                errorMessages = errorMessages - "email"
                            },
                            leadingIcon = Icons.Default.Email
                        )
                        if (errorMessages.containsKey("email")) {
                            Text(errorMessages["email"] ?: "", color = Color.Red, fontSize = 12.sp)
                        }

                        ReusableInputField(
                            label = "Contraseña",
                            value = password,
                            onValueChange = {
                                password = it
                                errorMessages = errorMessages - "password"
                            },
                            isPassword = true
                        )
                        if (errorMessages.containsKey("password")) {
                            Text(errorMessages["password"] ?: "", color = Color.Red, fontSize = 12.sp)
                        }

                        ReusableInputField(
                            label = "Confirmar Contraseña",
                            value = confirmPassword,
                            onValueChange = {
                                confirmPassword = it
                                errorMessages = errorMessages - "confirmPassword"
                            },
                            isPassword = true
                        )
                        if (errorMessages.containsKey("confirmPassword")) {
                            Text(errorMessages["confirmPassword"] ?: "", color = Color.Red, fontSize = 12.sp)
                        }

                        ReusableInputField(
                            label = "Localidad",
                            value = location,
                            onValueChange = {
                                location = it
                                errorMessages = errorMessages - "location"
                            },
                            leadingIcon = Icons.Default.LocationOn
                        )
                        if (errorMessages.containsKey("location")) {
                            Text(errorMessages["location"] ?: "", color = Color.Red, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                ButtonPrimary(
                    text = if (authResult is AuthResult.Loading) "Registrando..." else "Registrarse",
                    onClick = {
                        errorMessages = emptyMap() // Reinicia errores
                        authViewModel.registerUser(email, password, confirmPassword, name, surname, location)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(250.dp),
                    enabled = authResult !is AuthResult.Loading
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 🔹 **Mostrar Error General**
                if (errorMessages.containsKey("general")) {
                    Text(errorMessages["general"] ?: "", color = Color.Red, fontSize = 14.sp)
                }
            }
        }
    }
}



