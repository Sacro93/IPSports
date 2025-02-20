package com.example.ipsports.View.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.ipsports.R
import com.example.ipsports.View.Reusable.BottomNavigationBar
import com.example.ipsports.View.Reusable.FeatureCard
import com.example.ipsports.View.theme.Font.QuickSportsTitleGradient

@Composable
fun HomeScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    username: String,
    userImage: String? = null,  // Imagen de perfil (puede ser nula)
    userInitials: String = "FS", // Iniciales en caso de no haber imagen
    onEditProfile: () -> Unit,
    onStatsClick: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onNavigate = onNavigate,
                userImage = userImage,
                userInitials = userInitials,
                onProfileClick = onEditProfile
            )
        }
    ) { padding ->

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
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            Spacer(modifier = Modifier.height(24.dp))

            // 📌 **Nombre de la App**
            QuickSportsTitleGradient()

            Spacer(modifier = Modifier.height(24.dp))

            // 📌 **Tarjetas de eventos**
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FeatureCard(
                    imageRes = R.drawable.brc,
                    title = "Create Your Own Event",
                    onClick = { /* Acción de ejemplo */ }
                )
                FeatureCard(
                    imageRes = R.drawable.grupo,
                    title = "Find Your Sport Match",
                    onClick = { /* Acción de ejemplo */ }
                )
                EventCard(
                    sport = "Padel",
                    date = "27 de Enero, 2025",
                    location = "Club Deportivo Las Palmas",
                    participants = listOf("Ana", "Luis", "María", "Juan"),
                    maxParticipants = 8,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

// 📌 **Preview de la HomeScreen**
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

        HomeScreen(
            currentRoute = "home",
            onNavigate = { println("Navegar a $it") },
            username = "Francisco",
            userImage = null, // Puedes agregar una URL si el usuario tiene imagen
            userInitials = "FS",
            onEditProfile = { println("Editar perfil") },
            onStatsClick = { println("Ir a estadísticas") },
            onLogout = { println("Cerrar sesión") }
        )

}
