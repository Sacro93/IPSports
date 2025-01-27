package com.example.ipsports.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.R
import com.example.ipsports.View.Reusable.BottomNavigationBar
import com.example.ipsports.View.Reusable.EventCard
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF457B9D), // Azul profundo
                            Color(0xFFA8DADC)  // Azul claro
                        )
                    )
                )
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
// TopUserMenu en la parte superior

            TopUserMenu(
                userInitials = "JD", // Genera dinámicamente o asigna manualmente
                userImage = null,    // O pasa painterResource(id = R.drawable.tu_imagen)
                onEditProfile = { println("Editar perfil") },
                onStatsClick = { println("Ir a estadísticas") },
                onLogout = { println("Cerrar sesión") }
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Tarjetas de eventos
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
