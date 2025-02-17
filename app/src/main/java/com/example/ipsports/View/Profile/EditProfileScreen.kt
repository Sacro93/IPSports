package com.example.ipsports.View.Profile

import androidx.compose.ui.tooling.preview.Preview
import com.example.ipsports.View.Reusable.ButtonPrimary
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ipsports.View.Reusable.ReusableInputField

@Composable
fun EditProfileScreen(
    profileImage: String?, // Imagen de perfil (futuro: desde BD)
    userData: UserData, // Datos del usuario obtenidos desde la BD
    onPhotoSelected: () -> Unit,
    onTakePhoto: () -> Unit,
    onSaveChanges: (String, String, String, String, String, String) -> Unit,
    onBack: () -> Unit
) {
    var showPhotoDialog by remember { mutableStateOf(false) }

    // Estados controlados (futuro: reemplazados por ViewModel)
    var nombre by remember { mutableStateOf(userData.nombre) }
    var apellido by remember { mutableStateOf(userData.apellido) }
    var email by remember { mutableStateOf(userData.email) }
    var phone by remember { mutableStateOf(userData.phone) }
    var password by remember { mutableStateOf(userData.password) }
    var domicilio by remember { mutableStateOf(userData.domicilio) }

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

        // 📌 **Botón Guardar Cambios**
        ButtonPrimary(
            text = "Guardar Cambios",
            onClick = { onSaveChanges(nombre, apellido, email, phone, domicilio, password) },
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

// **📌 Modelo de datos para recibir desde BD**
data class UserData(
    val nombre: String,
    val apellido: String,
    val email: String,
    val phone: String,
    val domicilio: String,
    val password: String
)


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        profileImage = null, // Puedes cambiar esto para probar con una URL de imagen
        userData = UserData(
            nombre = "Francisco",
            apellido = "Santiago",
            email = "sacroisky93@example.com",
            phone = "+34 600 123 456",
            domicilio = "Barcelona, España",
            password = "***********"
        ),
        onPhotoSelected = { println("Seleccionar foto de galería") },
        onTakePhoto = { println("Abrir cámara") },
        onSaveChanges = { nombre, apellido, email, phone, domicilio, password ->
            println("Guardado: $nombre, $apellido, $email, $phone, $domicilio, $password")
        },
        onBack = { println("Volver") }
    )
}

