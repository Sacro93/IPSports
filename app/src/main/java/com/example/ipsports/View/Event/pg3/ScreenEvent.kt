package com.example.ipsports.View.Event.pg3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Event.ReusableEvent.EventCreationProgressBar
import com.example.ipsports.View.Reusable.ButtonPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenEvent(
    sport: String,
    date: String,
    location: String,
    maxParticipants: Int,
    selectedCourt: String,
    friends: List<String>,
    currentPage: Int,
    totalPages: Int,
    onConfirm: () -> Unit,
    onBack: () -> Unit = {} // Acción para volver atrás
) {
    Scaffold(
        topBar = {
            Spacer(modifier = Modifier.height(40.dp))

            TopAppBar(

                title = { Text("Resumen del Evento", color = Color.White) },
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
                            Color(0xFF337C8D), // Azul verdoso claro
                            Color(0xFF15272D), // Azul grisáceo oscuro
                            Color(0xFF17272B)  // Casi negro
                        )
                    )
                )
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Barra de progreso del evento
                EventCreationProgressBar(currentPage = currentPage, totalPages = totalPages)

                Spacer(modifier = Modifier.height(35.dp))

                // 📌 **Tarjeta de resumen del evento**
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
                ) {
                    EventSummaryScreen(
                        sport = sport,
                        date = date,
                        location = location,
                        maxParticipants = maxParticipants,
                        selectedCourt = selectedCourt,
                        friends = friends
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // ✔ **Botón de Confirmar**
                ButtonPrimary(
                    text = "Confirmar Evento",
                    onClick = onConfirm,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(250.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenEventPreview() {
    ScreenEvent(
        sport = "Padel",
        date = "27 de Enero, 2025",
        location = "Club Deportivo Las Palmas",
        maxParticipants = 8,
        selectedCourt = "Cancha 1",
        friends = listOf("Ana", "Luis", "María", "Juan"),
        currentPage = 2,
        totalPages = 5,
        onConfirm = { println("Evento confirmado") },
        onBack = { println("Volver atrás") }
    )
}

