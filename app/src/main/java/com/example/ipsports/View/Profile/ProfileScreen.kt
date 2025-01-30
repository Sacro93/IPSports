package com.example.ipsports.View.Profile
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.ipsports.ui.theme.IpSportsTheme

@Composable
fun ProfileScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    username: String,
    email: String,
    profileImage: String?,
    onUpgradeClick: () -> Unit,
    onHelpClick: () -> Unit,
    onAccountClick: () -> Unit,
    onDocumentsClick: () -> Unit,
    onLearnClick: () -> Unit,
    onInboxClick: () -> Unit
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
                            Color(0xFF1E88E5), // Azul brillante (inicio)
                            Color(0xFF1565C0), // Azul medio
                            Color(0xFF000000)  // Negro (final)
                        ),
                        startY = 0.0f,
                        endY = 2100f
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                // Imagen de perfil
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = username.firstOrNull()?.toString()?.uppercase() ?: "",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Nombre del usuario
                Text(
                    text = username,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary
                )

                // Email del usuario
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón Upgrade
                Button(
                    onClick = onUpgradeClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(text = "Upgrade")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Opciones del perfil
                ProfileOption(icon = Icons.Default.Help, label = "Help", onClick = onHelpClick)
                ProfileOption(icon = Icons.Default.Person, label = "Account", onClick = onAccountClick)
                ProfileOption(icon = Icons.Default.Description, label = "Documents & Statements", onClick = onDocumentsClick)
                ProfileOption(icon = Icons.Default.Lightbulb, label = "Learn", onClick = onLearnClick)
                ProfileOption(icon = Icons.Default.Email, label = "Inbox", onClick = onInboxClick)
            }
        }
    }
}

@Composable
fun ProfileOption(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ProfileScreenPreview() {
    IpSportsTheme {
        ProfileScreen(
            currentRoute = "profile",
            onNavigate = { println("Navigate to $it") },
            username = "Francisco Santiago",
            email = "sacroisky93@example.com",
            profileImage = null, // Cambia por una URL si tienes una imagen
            onUpgradeClick = { println("Upgrade clicked") },
            onHelpClick = { println("Help clicked") },
            onAccountClick = { println("Account clicked") },
            onDocumentsClick = { println("Documents clicked") },
            onLearnClick = { println("Learn clicked") },
            onInboxClick = { println("Inbox clicked") }
        )
    }
}

