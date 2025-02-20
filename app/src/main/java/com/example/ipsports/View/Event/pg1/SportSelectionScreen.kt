package com.example.ipsports.View.Event.pg1
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ipsports.R
import com.example.ipsports.View.Event.ReusableEvent.EventCreationProgressBar
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Event.pg2.SportSelectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportSelectionScreen(
    selectedSport: String?,            // Deporte actualmente seleccionado
    onSportSelected: (String) -> Unit, // Acción al seleccionar un deporte
    onBack: () -> Unit,                // Acción para volver atrás
    onContinue: () -> Unit             // Acción al presionar "Continuar"
) {
    val sports = listOf(
        Pair("Fútbol", R.drawable.futboll),
        Pair("Basket", R.drawable.basket),
        Pair("Paddle", R.drawable.paddle),
        Pair("Tenis", R.drawable.tennis)
    )

    Scaffold(
        topBar = {
            Spacer(modifier = Modifier.height(16.dp))
            TopAppBar(
                title = { Text("Selecciona un Deporte", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF337C8D), // Azul verdoso claro (parte superior)
                            Color(0xFF15272D), // Azul grisáceo oscuro (zona media)
                            Color(0xFF17272B)  // Casi negro (base)
                        )
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))


                EventCreationProgressBar(currentPage = 2, totalPages = 4)

                Spacer(modifier = Modifier.height(50.dp))


                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f) // Rellena el espacio disponible
                ) {
                    items(sports) { sport ->
                        SportSelectionCard(
                            imageRes = sport.second, // Recurso de la imagen
                            isSelected = selectedSport == sport.first, // Verifica si está seleccionado
                            onClick = { onSportSelected(sport.first) } // Acción al hacer clic
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                ButtonPrimary(
                    text = "Continuar",
                    onClick = onContinue,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(280.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

