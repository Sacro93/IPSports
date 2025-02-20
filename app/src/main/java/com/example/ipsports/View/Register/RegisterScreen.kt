package com.example.ipsports.View.Register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ipsports.Model.Auth.AuthResult
import com.example.ipsports.Model.RoutesNavigation.Routes
import com.example.ipsports.Model.ValidationUtils
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Reusable.LocationDropdown
import com.example.ipsports.View.Reusable.ReusableInputField
import com.example.ipsports.View.theme.Font.QS
import com.example.ipsports.ViewModel.Auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

/*Resumen y Consideraciones
Consolidamos la verificación de email en un único flujo usando un DisposableEffect para escuchar los cambios de autenticación, y un LaunchedEffect que reacciona cuando se activa el estado de verificación.
Se usan tres efectos:
Uno para manejar el estado de authResult (registro, error, loading).
Uno para escuchar emailVerificationResult y activar el diálogo de verificación.
Y otro DisposableEffect para escuchar cambios en el estado de autenticación (esto se encargará de redirigir si el email se verifica).
El botón de registro se deshabilita mientras isRegistering es true.
Los diálogos para notificar al usuario se muestran según los estados correspondientes.
Este flujo permite:

Enviar el correo de verificación al registrar.
Mostrar una notificación de que se envió el correo.
Detectar automáticamente cuando el usuario verifica su correo y redirigirlo a la pantalla de inicio de sesión.*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController,
    onBack: () -> Unit
) {
    // Estados para los campos del formulario
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("Barcelona") }

    // Estados para controlar la UI
    var isRegistering by remember { mutableStateOf(false) }
    var showEmailSentDialog by remember { mutableStateOf(false) }
    var showVerificationDialog by remember { mutableStateOf(false) }
    var errorMessages by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var isRegistrationSuccessful by remember { mutableStateOf(false) } // Nuevo estado



    // Validaciones en tiempo real
    val isEmailValid by remember {
        derivedStateOf { ValidationUtils.isValidEmail(email) }
    }

    val isPasswordValid by remember {
        derivedStateOf { ValidationUtils.isValidPassword(password) }
    }

    val doPasswordsMatch by remember {
        derivedStateOf { ValidationUtils.doPasswordsMatch(password, confirmPassword) }
    }

    if (password.isNotBlank() && !isPasswordValid) {
        Text("La contraseña debe tener al menos 8 caracteres y una mayúscula", color = Color.Red, fontSize = 12.sp)
    }

// Observar cambios en el resultado de autenticación
    val authResult by authViewModel.authResult.observeAsState()

    // Efecto para manejar el resultado de autenticación
    LaunchedEffect(authResult) {
        when (authResult) {
            is AuthResult.Loading -> isRegistering = true
            is AuthResult.Success -> {
                isRegistering = false
                showEmailSentDialog = true // Mostrar diálogo de correo enviado
                isRegistrationSuccessful = true // Marcar el registro como exitoso
            }
            is AuthResult.Failure -> {
                isRegistering = false
                errorMessages = mapOf("general" to ((authResult as AuthResult.Failure).exception.message ?: "Error desconocido"))
            }
            else -> Unit
        }
    }

    // Efecto para redirigir después de un registro exitoso
    LaunchedEffect(isRegistrationSuccessful) {
        if (isRegistrationSuccessful) {
            navController.navigate("${Routes.LOGIN}?showVerificationMessage=true") {
                popUpTo(Routes.REGISTER) { inclusive = true }
            }
        }
    }

    // Diálogo para notificar que se envió el email de verificación
    if (showEmailSentDialog) {
        AlertDialog(
            onDismissRequest = { showEmailSentDialog = false },
            title = { Text("Verificación enviada") },
            text = { Text("Debes corroborar tu correo electrónico para completar el registro.") },
            confirmButton = {
                Button(onClick = { showEmailSentDialog = false }) {
                    Text("Aceptar")
                }
            }
        )
    }



    // Scaffold con la estructura de la pantalla
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
                            Color(0xFF1E88E5),
                            Color(0xFF1565C0),
                            Color(0xFF000000)
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

                // Logo
                QS()

                Spacer(modifier = Modifier.height(16.dp))

                // Tarjeta con el formulario de registro
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Campo de nombre
                        ReusableInputField(
                            label = "Nombre",
                            value = name,
                            onValueChange = {
                                name = it
                                errorMessages = errorMessages - "name"
                            },
                            leadingIcon = Icons.Default.Person
                        )
                        if (errorMessages.containsKey("name")) {
                            Text(errorMessages["name"] ?: "", color = Color.Red, fontSize = 12.sp)
                        }

                        // Campo de apellido
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

                        // Campo de correo electrónico
                        ReusableInputField(
                            label = "Correo Electrónico",
                            value = email,
                            onValueChange = {
                                email = it
                                errorMessages = errorMessages - "email"
                            },
                            leadingIcon = Icons.Default.Email
                        )
                        if (email.isNotBlank() && !isEmailValid) {
                            Text("Correo electrónico no válido", color = Color.Red, fontSize = 12.sp)
                        }

                        // Campo de contraseña
                        ReusableInputField(
                            label = "Contraseña",
                            value = password,
                            onValueChange = {
                                password = it
                                errorMessages = errorMessages - "password"
                            },
                            isPassword = true
                        )
                        if (password.isNotBlank() && !isPasswordValid) {
                            Text("La contraseña debe tener al menos 8 caracteres y una mayúscula", color = Color.Red, fontSize = 12.sp)
                        }

                        // Campo de confirmación de contraseña
                        ReusableInputField(
                            label = "Confirmar Contraseña",
                            value = confirmPassword,
                            onValueChange = {
                                confirmPassword = it
                                errorMessages = errorMessages - "confirmPassword"
                            },
                            isPassword = true
                        )
                        if (confirmPassword.isNotBlank() && !doPasswordsMatch) {
                            Text("Las contraseñas no coinciden", color = Color.Red, fontSize = 12.sp)
                        }

                        // Selector de ubicación
                        LocationDropdown(
                            selectedLocation = location,
                            onLocationSelected = {
                                location = it
                                errorMessages = errorMessages - "location"
                            }
                        )
                        if (errorMessages.containsKey("location")) {
                            Text(errorMessages["location"] ?: "", color = Color.Red, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Botón de registro
                ButtonPrimary(
                    text = if (isRegistering) "Registrando" else "Registrarse",
                    onClick = {
                        if (!isRegistering) {
                            errorMessages = emptyMap() // Reinicia errores
                            authViewModel.registerUser(email, password, confirmPassword, name, surname, location)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(200.dp),
                    enabled = !isRegistering && isEmailValid && isPasswordValid && doPasswordsMatch
                )


                Spacer(modifier = Modifier.height(20.dp))

                // Mostrar errores generales
                if (errorMessages.containsKey("general")) {
                    Text(errorMessages["general"] ?: "", color = Color.Red, fontSize = 14.sp)
                }
            }
        }
    }
}