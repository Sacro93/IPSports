package com.example.ipsports.View.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.ipsports.View.Reusable.ButtonPrimary

@Composable
fun EditProfileScreen(
    profileImage: String?,
    onPhotoSelected: () -> Unit,
    onTakePhoto: () -> Unit,
    onSaveChanges: (String, String, String, String, String) -> Unit,
    onBack: () -> Unit,
    onSignOut: () -> Unit
) {
    var showPhotoDialog by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }

    // Estados para cada campo editable
    var nombre by remember { mutableStateOf("Francisco Santiago") }
    var email by remember { mutableStateOf("sacroisky93@example.com") }
    var phone by remember { mutableStateOf("+34 600 123 456") }
    var password by remember { mutableStateOf("***********") }
    var domicilio by remember { mutableStateOf("Barcelona, España") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E88E5), // Azul brillante (inicio)
                        Color(0xFF1565C0), // Azul medio
                        Color(0xFF000000)  // Negro (final)
                    )
                )
            )
    ) {
        // **Flecha para volver atrás**
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.White,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onBack() }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // 🔹 **Imagen de Perfil**
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .clickable { showPhotoDialog = true }, // 🔹 Ahora la imagen es clickeable
                    contentAlignment = Alignment.Center
                ) {
                    if (profileImage != null) {
                        AsyncImage(
                            model = profileImage,
                            contentDescription = "Imagen de perfil",
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 📌 **Texto para cambiar foto**
                Text(
                    text = "Cambiar Foto",
                    color = Color.White.copy(alpha = 0.8f), // 🔹 Texto sutilmente más tenue
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { showPhotoDialog = true } // 🔹 Clickeable para abrir diálogo
                )
            }
        }


        // **Campos editables**
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileEditableField("Nombre", nombre, isEditing) { nombre = it }
            ProfileEditableField("Email", email, isEditing) { email = it }
            ProfileEditableField("Número Móvil", phone, isEditing) { phone = it }
            ProfileEditableField("Domicilio", domicilio, isEditing) { domicilio = it }
            ProfileEditableField("Contraseña", password, isEditing, isPassword = true) {
                password = it
            }
        }


    }

    // **Diálogo para elegir foto**
    if (showPhotoDialog) {
        AlertDialog(
            onDismissRequest = { showPhotoDialog = false },
            title = { Text("Seleccionar Foto") },
            text = { Text("Elige cómo deseas añadir tu foto de perfil") },
            confirmButton = {
                Column {
                    ButtonPrimary(
                        text = "Tomar Foto",
                        onClick = {
                            showPhotoDialog = false
                            onTakePhoto()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ButtonPrimary(
                        text = "Seleccionar de Galería",
                        onClick = {
                            showPhotoDialog = false
                            onPhotoSelected()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            dismissButton = {
                ButtonPrimary(
                    text = "Cancelar",
                    onClick = { showPhotoDialog = false },
                    isOutlined = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}

// **Componente para cada campo editable**
@Composable
fun ProfileEditableField(
    label: String,
    value: String,
    isEditing: Boolean,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(value) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it; onValueChange(it) },
        label = { Text(label, color = Color.Gray) },
        trailingIcon = {
            if (isEditing) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = Color.Gray,
                    modifier = Modifier.clickable { /* Agregar lógica si es necesario */ }
                )
            }
        },
        readOnly = !isEditing,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.5f)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        profileImage = null,
        onPhotoSelected = { println("Seleccionar foto de galería") },
        onTakePhoto = { println("Abrir cámara") },
        onSaveChanges = { nombre, email, phone, domicilio, password ->
            println("Guardado: $nombre, $email, $phone, $domicilio, $password")
        },
        onBack = { println("Volver") },
        onSignOut = { println("Cerrar sesión") }
    )
}
