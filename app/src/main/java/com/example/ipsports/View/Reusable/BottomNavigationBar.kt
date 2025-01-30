package com.example.ipsports.View.Reusable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ipsports.ui.theme.IpSportsTheme


@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color.Black,
        tonalElevation = 8.dp,// Elevación ligera
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
    }
}



data class NavigationItem(val route: String, val icon: ImageVector, val label: String)

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    IpSportsTheme {
        BottomNavigationBar(
            currentRoute = "home",
            onNavigate = { route -> println("Navigating to $route") }
        )
    }
}