package com.example.ipsports.View.Profile

import com.example.ipsports.View.Reusable.ButtonPrimary
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ipsports.View.Reusable.ReusableInputField
import com.example.ipsports.ViewModel.ui.UserViewModel
import com.example.ipsports.data.model.User
import com.google.firebase.auth.FirebaseAuth

@Composable
fun EditProfileScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    profileImage: String?,
    userData: User,
    onPhotoSelected: () -> Unit,
    onTakePhoto: () -> Unit,
    onBack: () -> Unit
) {

    // Estados controlados (futuro: reemplazados por ViewModel)
    var showPhotoDialog by remember { mutableStateOf(false) }

    // Estados controlados (ahora cargados desde `userData`)
    var nombre by remember { mutableStateOf(userData.name) }
    var apellido by remember { mutableStateOf(userData.surname) }
    var email by remember { mutableStateOf(userData.email) }
    var phone by remember { mutableStateOf("") }  // Agregar si el modelo tiene phone
    var password by remember { mutableStateOf("") }  // No se debe almacenar la contraseña directamente
    var domicilio by remember { mutableStateOf(userData.location) }

    val userId = FirebaseAuth.getInstance().currentUser?.uid

    Column(
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
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔹 **Flecha para volver**
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

        // 🔹 **Imagen de perfil**
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .clickable { showPhotoDialog = true },
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

        Text(
            text = "Cambiar Foto",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable { showPhotoDialog = true }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 📌 **Formulario dentro de una tarjeta**
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ReusableInputField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = "Nombre",
                    leadingIcon = Icons.Default.Person
                )
                ReusableInputField(
                    value = apellido,
                    onValueChange = { apellido = it },
                    label = "Apellido",
                    leadingIcon = Icons.Default.Person
                )
                ReusableInputField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    leadingIcon = Icons.Default.Email
                )
                ReusableInputField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = "Número Móvil",
                    leadingIcon = Icons.Default.Phone
                )
                ReusableInputField(
                    value = domicilio,
                    onValueChange = { domicilio = it },
                    label = "Domicilio",
                    leadingIcon = Icons.Default.LocationOn
                )
                ReusableInputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    isPassword = true,
                    leadingIcon = Icons.Default.Lock
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        ButtonPrimary(
            text = "Guardar Cambios",
            onClick = {
                val updatedUser = User(
                    id = userId ?: "",
                    name = nombre,
                    surname = apellido,
                    email = email,
                    location = domicilio,
                    profileImageUrl = profileImage ?: "" // 🔹 Asegurar que no sea null
                )


                userViewModel.updateUser(updatedUser) // ✅ Llamamos a updateUser(user)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(250.dp)
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

