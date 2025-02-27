package com.example.ipsports.View.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ipsports.data.Auth.AuthResult
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Reusable.ReusableInputField
import com.example.ipsports.View.theme.Font.QuickSportsTitleGradient
import com.example.ipsports.ViewModel.Autenticacion.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onRegisterClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessages by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var showVerificationDialog by remember { mutableStateOf(false) }

    val authResult by authViewModel.authResult.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
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
                Spacer(modifier = Modifier.height(20.dp))

                QuickSportsTitleGradient()

                Spacer(modifier = Modifier.height(90.dp))

                Text(
                    text = "Iniciar Sesión",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 28.sp
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
                    if (errorMessages.containsKey("email")) {
                        Text(
                            text = errorMessages["email"] ?: "",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }

                    ReusableInputField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Contraseña",
                        placeholder = "Escribe tu contraseña...",
                        leadingIcon = Icons.Default.Lock,
                        isPassword = true
                    )
                    if (errorMessages.containsKey("password")) {
                        Text(
                            text = errorMessages["password"] ?: "",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    when (authResult) {

                        is AuthResult.Success -> {
                            onNavigateToHome()
                        }

                        is AuthResult.Failure -> {
                            val error = (authResult as AuthResult.Failure).exception.message
                                ?: "Ocurrió un error inesperado"

                            errorMessages = when {
                                "correo" in error.lowercase() -> errorMessages + ("email" to error)
                                "contraseña" in error.lowercase() -> errorMessages + ("password" to error)
                                "verificar" in error.lowercase() -> {
                                    showVerificationDialog = true
                                    errorMessages + ("general" to error)
                                }
                                else -> errorMessages + ("general" to error)
                            }

                            LaunchedEffect(snackbarHostState) {
                                snackbarHostState.showSnackbar(error)
                            }

                        }

                        else -> Unit
                    }

                    ButtonPrimary(
                        text = "Iniciar Sesión",
                        onClick = {
                            errorMessages = emptyMap()
                            authViewModel.loginUser(email, password)
                        },
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

        if (showVerificationDialog) {
            AlertDialog(
                onDismissRequest = { showVerificationDialog = false },
                title = { Text("Correo no verificado") },
                text = { Text("Debes verificar tu correo antes de iniciar sesión. ¿Quieres que te reenviemos el correo de verificación?") },
                confirmButton = {
                    Button(onClick = {
                        authViewModel.sendVerificationEmail()
                        showVerificationDialog = false
                    }) {
                        Text("Reenviar correo")
                    }
                },
                dismissButton = {
                    Button(onClick = { showVerificationDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

/*
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),  // 🔹 ViewModel para manejar autenticación
    onNavigateToHome: () -> Unit, //Acción cuando el login es exitoso
    onRegisterClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessages by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var showVerificationDialog by remember { mutableStateOf(false) }

    val authResult by authViewModel.authResult.observeAsState()

    LaunchedEffect(authResult) {
        when (authResult) {
            is AuthResult.Failure -> {
                val error = (authResult as AuthResult.Failure).exception.message ?: "Ocurrió un error inesperado"

                errorMessages = when {
                    "correo" in error.lowercase() -> errorMessages + ("email" to error)
                    "contraseña" in error.lowercase() -> errorMessages + ("password" to error)
                    "verificar" in error.lowercase() -> {
                        showVerificationDialog = true // ✅ Mostrar diálogo si es un problema de verificación
                        errorMessages + ("general" to error)
                    }
                    else -> errorMessages + ("general" to error)
                }
            }

            is AuthResult.Success -> {
                onNavigateToHome() // ✅ Redirigir si el login es exitoso
            }

            else -> Unit
        }
    }

    if (showVerificationDialog) {
        AlertDialog(
            onDismissRequest = { showVerificationDialog = false },
            title = { Text("Correo no verificado") },
            text = { Text("Debes verificar tu correo antes de iniciar sesión. ¿Quieres que te reenviemos el correo de verificación?") },
            confirmButton = {
                Button(onClick = {
                    authViewModel.sendVerificationEmail()
                    showVerificationDialog = false
                }) {
                    Text("Reenviar correo")
                }
            },
            dismissButton = {
                Button(onClick = { showVerificationDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
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
                if (errorMessages.containsKey("email")) {
                    Text(
                        text = errorMessages["email"] ?: "",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                ReusableInputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    placeholder = "Escribe tu contraseña...",
                    leadingIcon = Icons.Default.Lock,
                    isPassword = true
                )
                if (errorMessages.containsKey("password")) {
                    Text(
                        text = errorMessages["password"] ?: "",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (authResult is AuthResult.Loading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    ButtonPrimary(
                        text = "Iniciar Sesión",
                        onClick = {
                            errorMessages = emptyMap()
                            authViewModel.loginUser(email, password)
                        },
                        modifier = Modifier
                            .width(280.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

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

*/
