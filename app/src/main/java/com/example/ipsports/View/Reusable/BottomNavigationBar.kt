package com.example.ipsports.View.Reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    userImage: String? = null, // Acepta nulo si el usuario no tiene imagen
    userInitials: String = "",
    onProfileClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.Black,
        tonalElevation = 8.dp, // Elevación ligera
        modifier = Modifier.height(56.dp) // Altura más baja
    ) {
        val items = listOf(
            NavigationItem(route = "home", icon = Icons.Default.Home, label = "Menu"),
            NavigationItem(route = "fields", icon = Icons.Default.LocationOn, label = "Centers"),
            NavigationItem(route = "friends", icon = Icons.Default.PersonAdd, label = "Add Friends")
        )

        items.forEach { item ->
            NavigationBarItem(
                selected = false, // Forzar que no se seleccione
                onClick = { onNavigate(item.route) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(2.dp) // Espacio reducido entre icono y texto
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = Color.White, // Íconos siempre blancos
                            modifier = Modifier.size(24.dp) // Tamaño de ícono
                        )
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            color = Color.White // Texto siempre blanco
                        )
                    }
                },
                alwaysShowLabel = true // Mostrar etiquetas siempre
            )
        }

        // 📌 **Miniatura del Perfil a la Derecha**
        NavigationBarItem(
            selected = false, // No seleccionable
            onClick = onProfileClick, // Acción al hacer clic en el perfil
            icon = {
                if (userImage != null) {
                    AsyncImage(
                        model = userImage,
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape) // Hacerlo circular
                            .border(2.dp, Color.White, CircleShape) // Borde blanco
                    )
                } else {
                    // Si no hay imagen, muestra las iniciales en un círculo
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = userInitials,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            },
            alwaysShowLabel = false // No mostrar etiqueta debajo
        )
    }
}



data class NavigationItem(val route: String, val icon: ImageVector, val label: String)

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        currentRoute = "home",
        onNavigate = { println("Navegar a: $it") },
        userImage = null,  // Puedes pasar una URL si la imagen existe
        userInitials = "FS",
        onProfileClick = { println("Ir a perfil") }
    )
}
