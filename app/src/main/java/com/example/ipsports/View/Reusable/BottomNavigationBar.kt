package com.example.ipsports.View.Reusable


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.ipsports.data.routesNavigation.Routes


// ✅ Modelo de datos para la navegación
data class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val label: String

)

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,

) {
    val items = listOf(
        NavigationItem(route = Routes.HOME, icon = Icons.Default.Home, label = "Inicio"),
        NavigationItem(route = Routes.CENTERS, icon = Icons.Default.LocationOn, label = "Centros"),
        NavigationItem(route = Routes.FRIENDS, icon = Icons.Default.PersonAdd, label = "Amigos"),
        NavigationItem(route = Routes.PROFILE, icon = Icons.Default.Person, label = "Perfil")
    )

    NavigationBar(
        containerColor = Color.Black,
        tonalElevation = 8.dp,
        modifier = Modifier.height(56.dp)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (currentRoute == item.route) MaterialTheme.colorScheme.primary else Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (currentRoute == item.route) MaterialTheme.colorScheme.primary else Color.White
                    )
                },
                alwaysShowLabel = true
            )
        }



    }
}



