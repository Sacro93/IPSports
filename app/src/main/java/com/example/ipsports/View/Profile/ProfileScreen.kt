package com.example.ipsports.View.Profile
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ipsports.View.Reusable.BottomNavigationBar

@Composable
fun ProfileScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    username: String,
    email: String,
    profileImage: String?,
    onHelpClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onTermsClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onCloseAccountClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onNavigate = onNavigate
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(

                            Color(0xFF1565C0), // Azul medio
                            Color(0xFF1565C0), // Azul elegante
                            Color(0xFF1E293B), // Azul oscuro con gris
                          //  Color(0xFF000000),  // Negro (final)
                            Color(0xFF000000)  // Negro (final)

                        )
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(200.dp))


                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (profileImage != null) {
                        AsyncImage(
                            model = profileImage,
                            contentDescription = "Imagen de perfil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Text(
                            text = username.firstOrNull()?.toString()?.uppercase() ?: "",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 🔹 Nombre y email estilizados
                Text(
                    text = username,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )

                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 🔹 Card con opciones de perfil
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF121212))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        ProfileOption(icon = Icons.Default.Help, label = "Ayuda", onClick = onHelpClick)
                        ProfileOption(icon = Icons.Default.Person, label = "Editar Perfil", onClick = onEditProfileClick)
                        ProfileOption(icon = Icons.Default.Article, label = "Términos y Condiciones", onClick = onTermsClick)
                        ProfileOption(icon = Icons.Default.Notifications, label = "Notificaciones", onClick = onNotificationsClick)
                        ProfileOption(icon = Icons.Default.Warning, label = "Cerrar Cuenta", onClick = onCloseAccountClick, isWarning = false)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileOption(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    isWarning: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isWarning) Color.Red else Color.White,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isWarning) Color.Red else Color.White
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        currentRoute = "profile",
        onNavigate = { println("Navigate to $it") },
        username = "Francisco Santiago",
        email = "sacroisky93@example.com",
        profileImage = null,
        onHelpClick = { println("Ayuda clickeada") },
        onEditProfileClick = { println("Editar perfil clickeado") },
        onTermsClick = { println("Términos y condiciones clickeados") },
        onNotificationsClick = { println("Notificaciones clickeadas") },
        onCloseAccountClick = { println("Cerrar cuenta clickeado") }
    )
}

