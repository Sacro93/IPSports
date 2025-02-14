package com.example.ipsports.View.Profile

import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.ipsports.View.Reusable.ButtonPrimary
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun EditProfileScreen(
    profileImage: String?,
    onPhotoSelected: () -> Unit,
    onTakePhoto: () -> Unit,
    onSaveChanges: () -> Unit,
    onSignOut: () -> Unit
) {
    var showPhotoDialog by remember { mutableStateOf(false) }

    var nombre by remember { mutableStateOf("Aliza Khan") }
    var email by remember { mutableStateOf("alizakhan@gmail.com") }
    var phone by remember { mutableStateOf("+9230245963210") }
    var password by remember { mutableStateOf("***********") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // **Encabezado con imagen de perfil**
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFFA8DADC)), // Color similar al de la imagen
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .clickable { showPhotoDialog = true },
                contentAlignment = Alignment.Center
            ) {
                if (profileImage != null) {
                    AsyncImage(
                        model = profileImage,
                        contentDescription = "Imagen de perfil",
                        modifier = Modifier.clip(CircleShape)
                    )
                }
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Editar foto",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(24.dp)
                        .background(Color.Black.copy(alpha = 0.7f), shape = CircleShape)
                        .padding(4.dp)
                )
            }
        }

        // **Campos editables**
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileEditableField("Nombre", nombre) { nombre = it }
            ProfileEditableField("Email", email) { email = it }
            ProfileEditableField("Número Móvil", phone) { phone = it }
            ProfileEditableField("Contraseña", password, isPassword = true) { password = it }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // **Botón de Cerrar Sesión**
        ButtonPrimary(
            text = "Cerrar Sesión",
            onClick = onSignOut,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )
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
fun ProfileEditableField(label: String, value: String, isPassword: Boolean = false, onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf(value) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it; onValueChange(it) },
        label = { Text(label, color = Color.Gray) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = Color.Gray,
                modifier = Modifier.clickable { /* Agregar lógica si es necesario */ }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
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
        onSaveChanges = { println("Cambios guardados") },
        onSignOut = { println("Cerrar sesión") }
    )
}
