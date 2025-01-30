package com.example.ipsports.View.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.R
import com.example.ipsports.View.Reusable.BottomNavigationBar
import com.example.ipsports.View.Reusable.FeatureCard
import com.example.ipsports.View.Reusable.TopUserMenu
import com.example.ipsports.ui.theme.IpSportsTheme

@Composable
fun HomeScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    username: String,
    onEditProfile: () -> Unit,
    onStatsClick: () -> Unit,
    onLogout: () -> Unit
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
                            Color(0xFF1E88E5),// Azul brillante (inicio)
                            Color(0xFF1565C0), // Azul medio
                         //   Color(0xFF0D47A1), // Azul más oscuro
                            Color(0xFF000000)  // Negro (final)
                        ),
                        startY = 0.0f,
                       endY = 1000f
                    )
                )
        ) {
            // Overlay para suavizar el cambio
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1E88E5), // Azul brillante inicial
//                                Color(0xFF64B5F6), // Azul claro en el medio
                                Color(0xFF0D47A1), // Azul oscuro
                                Color.Black         // Negro
                            ),
                            startY = 0f,
                            endY = 1700f // Extender el rango del gradiente para suavidad
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                TopUserMenu(
                    userInitials = "JD",
                    userImage = null,
                    onEditProfile = onEditProfile,
                    onStatsClick = onStatsClick,
                    onLogout = onLogout
                )

                Spacer(modifier = Modifier.height(16.dp))

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
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IpSportsTheme {
        HomeScreen(
            currentRoute = "home",
            onNavigate = { println("Navegar a $it") },
            username = "Francisco",
            onEditProfile = { println("Editar perfil") },
            onStatsClick = { println("Ir a estadísticas") },
            onLogout = { println("Cerrar sesión") }
        )
    }
}
