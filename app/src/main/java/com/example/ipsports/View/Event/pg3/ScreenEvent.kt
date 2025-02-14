package com.example.ipsports.View.Event.pg3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Event.ReusableEvent.EventCreationProgressBar
import com.example.ipsports.View.Event.ReusableEvent.EventSummaryScreen
import com.example.ipsports.View.Reusable.ButtonPrimary

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
    onConfirm: () -> Unit
) {
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
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            // Barra de progreso del evento
            EventCreationProgressBar(currentPage = currentPage, totalPages = totalPages)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Resumen del Evento",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta del evento (EventSummaryScreen)
            EventSummaryScreen(
                sport = sport,
                date = date,
                location = location,
                maxParticipants = maxParticipants,
                selectedCourt = selectedCourt,
                friends = friends
            )

      //      Spacer(modifier = Modifier.height(100.dp))

           Spacer(modifier = Modifier.weight(0.5f))

            ButtonPrimary(
                text = "Confirmar Evento",
                onClick = onConfirm,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally),


            isOutlined = false // Botón sólido
            )
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
        onConfirm = { println("Evento confirmado") }
    )
}
